package com.zerobase.auction_project.auction.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDto {

    private String title;
    private Integer startPrice;
    private Integer highestBidPrice;
    private String image;
}
