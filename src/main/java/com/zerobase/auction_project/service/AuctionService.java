package com.zerobase.auction_project.service;

import com.zerobase.auction_project.domain.Auction;
import com.zerobase.auction_project.domain.dto.AddAuctionForm;
import com.zerobase.auction_project.domain.dto.UpdateAuctionForm;
import org.springframework.stereotype.Service;

@Service
public interface AuctionService {

    Auction enrollAuction(Long productId, AddAuctionForm form);

    Auction updateProduct(Long userId, UpdateAuctionForm form);


    void deleteAuction(Long userId, Long productId);

    Auction getAuction(Long productId);

}
