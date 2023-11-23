package com.zerobase.auction_project.auction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    //경매전
    Pending,
    //진행중
    OnGoing,
    //낙찰
    Sold,
    //유찰
    UnSold;

}
