package com.zerobase.auction_project.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@ToString
public class Record {

    @Id
    private Long Id;

    private String auctionId;
    private String buyerId;
    private String startPrice;
    private String endPrice;
    private String Time;
    private String status;
// 거래에 관한 추가 정보 , 결제,
    private String paymentMethod; // 결제 방법 enum 신용카드, 계좌 이체
    private String paymentStatus; // 결제 상태 enum 대기중, 완료, 취소
    private String ShippingStatus; // 배송 상태 enum 준비중, 배송중, 수령완료


    // 거래가 종료된 경매 기록을 따로 관리?
}
