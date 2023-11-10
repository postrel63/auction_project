package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.components.RedisClient;
import com.zerobase.auction_project.domain.Auction;
import com.zerobase.auction_project.domain.Bid;
import com.zerobase.auction_project.domain.request.BidForm;
import com.zerobase.auction_project.domain.response.BidResponse;
import com.zerobase.auction_project.domain.type.BidStatus;
import com.zerobase.auction_project.exception.CustomException;
import com.zerobase.auction_project.exception.ErrorCode;
import com.zerobase.auction_project.repository.AuctionRepository;
import com.zerobase.auction_project.service.BidService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final AuctionRepository auctionRepository;
    private final RedisClient redisClient;
    private final RedissonClient redissonClient;

    //입찰 시 레디스에 저장 (-> 나중에 DB에 한번에 저장)
    @Override
    @Transactional
    public BidResponse Bid(Long userId, BidForm form) {
        Optional<Auction> optionalAuction = auctionRepository.findById(form.getAuctionId());
        if (optionalAuction.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_AUCTION);
        }
        Auction auction = optionalAuction.get();

        // 새로운 입찰가 < 기존 최고 입찰가 이면 입찰X
        synchronized (auction) {
            Bid currentBid = redisClient.getBid(auction.getId(), userId);
            if (currentBid.getPrice() >= form.getBidPrice()) {
                return new BidResponse(BidStatus.BID_LOW_PRICE);
            }
            Bid newBid = Bid.builder()
                    .auction(auction)
                    .price(form.getBidPrice())
                    .userId(userId)
                    .bidAt(LocalDateTime.now())
                    .build();
            redisClient.putBid(form.getAuctionId(), userId, newBid);
        }
        return new BidResponse(BidStatus.SUCCESS);
    }

    @Override
    public void bidEnded() {

    }
}