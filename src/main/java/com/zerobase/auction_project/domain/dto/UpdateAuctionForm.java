package com.zerobase.auction_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuctionForm {

    //업데이트는 상품 이름과 설명만, 가격은 입찰이 없는 경우만 가능

    private Long productId;
    private String title;
    private String image;
    private Integer startPrice;
    private Integer endPrice;
    private LocalDateTime endTime;
}
