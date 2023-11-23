package com.zerobase.auction_project.auction.service;

import com.zerobase.auction_project.auction.domain.Auction;
import com.zerobase.auction_project.auction.domain.AuctionDto;
import com.zerobase.auction_project.auction.domain.AddAuctionForm;
import com.zerobase.auction_project.auction.domain.UpdateAuctionForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuctionService {

    Auction enrollAuction(Long userId, AddAuctionForm form);

    void endAuction(Long auctionId);

    Auction updateAuction(Long userId, UpdateAuctionForm form);


    String deleteAuction(Long userId, Long auctionId);

    Auction getAuction(Long productId);

    List<AuctionDto> getAllAuctions();

    Auction findByAuctionId(Long auctionId);

}
