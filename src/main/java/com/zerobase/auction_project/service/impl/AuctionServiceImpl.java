package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.domain.Auction;
import com.zerobase.auction_project.domain.dto.AddAuctionForm;
import com.zerobase.auction_project.domain.dto.UpdateAuctionForm;
import com.zerobase.auction_project.exception.CustomException;
import com.zerobase.auction_project.exception.ErrorCode;
import com.zerobase.auction_project.repository.AuctionRepository;
import com.zerobase.auction_project.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    //경매 상품 등록
    @Override
    @Transactional
    public Auction enrollAuction(Long productId, AddAuctionForm form) {
        return  auctionRepository.save(Auction.of(productId, form));
    }

    //경매 상품 수정
    @Override
    @Transactional
    public Auction updateProduct(Long userId, UpdateAuctionForm form) {
        Auction auction = auctionRepository.findBySellerIdAndId(userId, form.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUCTION));
        //입찰이 존재하면 더 이상 수정 불가
        if (auction.hasBid()){
            throw new CustomException(ErrorCode.BIDDING_INITIATED);
        }
        auction.updateAuction(userId,form);
        return auctionRepository.save(auction);
    }

    //경매 상품 삭제
    @Override
    @Transactional
    public void deleteAuction(Long userId, Long productId) {
        Auction auction = auctionRepository.findBySellerIdAndId(userId, productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_AUCTION));
        //입찰이 존재하면 더 이상 삭제 불가
        if (auction.hasBid()){
            throw new CustomException(ErrorCode.BIDDING_INITIATED);
        }
        auctionRepository.delete(auction);
    }

    //경매 상품 보기
    @Override
    public Auction getAuction(Long productId) {
        return auctionRepository.findById(productId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_AUCTION));
    }
}

