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
public class Bid {

    @Id
    private Long id;

    private String auctionId;
    private String memberId;
    private String price;
    private String createdAt;
}
