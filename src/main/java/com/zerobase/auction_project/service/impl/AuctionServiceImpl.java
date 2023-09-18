package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.components.AuctionEndJob;
import com.zerobase.auction_project.domain.Auction;
import com.zerobase.auction_project.domain.dto.AuctionDto;
import com.zerobase.auction_project.domain.request.AddAuctionForm;
import com.zerobase.auction_project.domain.request.UpdateAuctionForm;
import com.zerobase.auction_project.domain.response.AuctionListResponse;
import com.zerobase.auction_project.domain.type.ProductStatus;
import com.zerobase.auction_project.exception.CustomException;
import com.zerobase.auction_project.exception.ErrorCode;
import com.zerobase.auction_project.repository.AuctionRepository;
import com.zerobase.auction_project.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final Scheduler scheduler;

    //경매 상품 등록
    @Override
    @Transactional
    public Auction enrollAuction(Long userId, AddAuctionForm form) {
        Auction auction = auctionRepository.save(Auction.of(userId, form));

        scheduleEndAuction(auction.getId(),auction.getEndTime());
        return auction;
    }

    //경매 종료 스케줄러
    private void scheduleEndAuction(Long auctionId, LocalDateTime endTime){
        JobDetail jobDetail = buildJobDetail(auctionId);
        Trigger trigger = buildTrigger(auctionId,endTime);
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    private JobDetail buildJobDetail(Long auctionId){
        return JobBuilder.newJob(AuctionEndJob.class)
                .withIdentity(getJobIdentity(auctionId))
                .usingJobData("auctionId",String.valueOf(auctionId))
                .build();
    }

    private String getJobIdentity(Long auctionId){
        return "auctionEndJob:"+auctionId;
    }

    private Trigger buildTrigger(Long auctionId, LocalDateTime endTime){
        return TriggerBuilder.newTrigger()
                .withIdentity(getTriggerIdentity(auctionId),"auctionGroup")
                .startAt(Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
    private String getTriggerIdentity(Long auctionId){
        return "trigger:" + auctionId;
    }


    //경매 종료(스케줄러 처리)
    @Override
    @Transactional
    public void endAuction(Long auctionId) {
        Optional<Auction> auctionOptional = auctionRepository.findById(auctionId);
        if (auctionOptional.isPresent()){
            Auction auction = auctionOptional.get();
            if (auction.hasBid()){
                auction.setStatus(ProductStatus.Sold);
            }else{
                auction.setStatus(ProductStatus.UnSold);
            }
            auctionRepository.save(auction);
        }else{
            throw new CustomException(ErrorCode.NOT_FOUND_AUCTION);
        }
    }


    //경매 상품 수정
    @Override
    @Transactional
    public Auction updateAuction(Long userId, UpdateAuctionForm form) {
        Auction auction = auctionRepository.findBySellerIdAndId(userId, form.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUCTION));
        //입찰이 존재하면 더 이상 수정 불가
        if (auction.hasBid()) {
            throw new CustomException(ErrorCode.BIDDING_INITIATED);
        }
        auction.updateAuction(userId, form);
        return auctionRepository.save(auction);
    }

    //경매 상품 삭제
    @Override
    @Transactional
    public String deleteAuction(Long userId, Long auctionId){
        Auction auction = auctionRepository.findBySellerIdAndId(userId, auctionId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUCTION));
        //입찰이 존재하면 더 이상 삭제 불가
        if (auction.hasBid()) {
            throw new CustomException(ErrorCode.BIDDING_INITIATED);
        }
        auctionRepository.delete(auction);

        return "DELETE AUCTION";
    }

    //경매 상품 보기
    @Override
    public Auction getAuction(Long auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_AUCTION));
    }

    //경매 상품 모두 보기
    @Override
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctionList = auctionRepository.findAll();
        return auctionList.stream()
                .map(this::AuctionToDto)
                .collect(Collectors.toList());

    }

    private AuctionDto AuctionToDto(Auction auction) {
        Integer highestBidPrice = null;
        if (!auction.hasBid()) {
            highestBidPrice = auction.getHighestBidPrice();
        }
        return AuctionDto.builder()
                .highestBidPrice(highestBidPrice)
                .startPrice(auction.getStartPrice())
                .title(auction.getTitle())
                .image(auction.getImage())
                .build();
    }

}

