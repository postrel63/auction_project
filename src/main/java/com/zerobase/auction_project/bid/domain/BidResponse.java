package com.zerobase.auction_project.bid.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BidResponse {

    private BidStatus status;
    private String message;
    private Bid bid;

    public BidResponse(BidStatus bidStatus) {
    }
}

