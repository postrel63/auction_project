package com.zerobase.auction_project.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@RedisHash("bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long auctionProductId;
    private Long userId;
    private double price;
    private LocalDateTime bidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;




}
