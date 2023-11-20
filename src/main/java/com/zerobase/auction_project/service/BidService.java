package com.zerobase.auction_project.service;

import com.zerobase.auction_project.domain.Bid;
import com.zerobase.auction_project.domain.User;
import com.zerobase.auction_project.domain.request.BidForm;
import com.zerobase.auction_project.domain.response.BidResponse;
import org.springframework.stereotype.Service;

@Service
public interface BidService {

    BidResponse Bid(Long userId, BidForm form);

    void bidEnded();

    Bid bidUser(Long auctionId);

}
