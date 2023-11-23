package com.zerobase.auction_project.auction.domain;

import com.zerobase.auction_project.auction.domain.AuctionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuctionListResponse {

    private List<AuctionDto> auctions;
}
