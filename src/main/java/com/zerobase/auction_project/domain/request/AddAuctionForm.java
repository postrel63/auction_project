package com.zerobase.auction_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAuctionForm {

    private String title;
    private String image;
    private Integer startPrice;
    private Integer endPrice;
    private LocalDateTime endTime;

}
