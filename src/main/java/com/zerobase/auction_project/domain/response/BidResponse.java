package com.zerobase.auction_project.domain.response;

import com.zerobase.auction_project.domain.Bid;
import com.zerobase.auction_project.domain.type.BidStatus;
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

