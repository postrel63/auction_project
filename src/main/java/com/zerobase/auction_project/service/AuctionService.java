package com.zerobase.auction_project.service;

import com.zerobase.auction_project.domain.Auction;
import com.zerobase.auction_project.domain.dto.AuctionDto;
import com.zerobase.auction_project.domain.request.AddAuctionForm;
import com.zerobase.auction_project.domain.request.UpdateAuctionForm;
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

}
