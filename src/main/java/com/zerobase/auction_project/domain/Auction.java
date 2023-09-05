package com.zerobase.auction_project.domain;


import com.zerobase.auction_project.domain.type.Auctionstatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@ToString
public class Auction {

    @Id
    private Long id;

    private Long productId;
    private String buyerId;
    private Long minBidPrice; // 최소 입찰 증가 단위 금액
    private Long startPrice;
    private Long endPrice;
    private Date startTime;
    private Date endTime;
    private Auctionstatus status; // 상태

}
