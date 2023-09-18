package com.zerobase.auction_project.domain.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BidForm {

    private Long userId;
    private Long auctionId;
    private Integer bidPrice;


}
