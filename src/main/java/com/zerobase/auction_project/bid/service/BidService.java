package com.zerobase.auction_project.bid.service;

import com.zerobase.auction_project.bid.domain.Bid;
import com.zerobase.auction_project.bid.domain.BidForm;
import com.zerobase.auction_project.bid.domain.BidResponse;
import org.springframework.stereotype.Service;

@Service
public interface BidService {

    BidResponse Bid(Long userId, BidForm form);

    void bidEnded();

    Bid bidUser(Long auctionId);

}
