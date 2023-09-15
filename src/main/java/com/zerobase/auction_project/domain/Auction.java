package com.zerobase.auction_project.domain;


import com.zerobase.auction_project.domain.dto.AddAuctionForm;
import com.zerobase.auction_project.domain.dto.UpdateAuctionForm;
import com.zerobase.auction_project.domain.type.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerId;
    private String buyerId;
    private String title;
    private String image;
    private LocalDateTime createdTime;
    private Integer minBidPrice; // 최소 입찰 증가 단위 금액
    private Integer startPrice;
    private Integer endPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ProductStatus status; // 상태

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bid> bids;


    public static Auction of(Long userId, AddAuctionForm form){
        return Auction.builder()
                .sellerId(userId)
                .title(form.getTitle())
                .image(form.getImage())
                .createdTime(LocalDateTime.now())
                .startPrice(form.getStartPrice())
                .endPrice(form.getEndPrice())
                .startTime(LocalDateTime.now())
                .endTime(form.getEndTime())
                .build();
    }

    public  void updateAuction(Long userId, UpdateAuctionForm form){
        this.sellerId = userId;
        this.title = form.getTitle();
        this.image = form.getImage();
        this.startPrice = form.getStartPrice();
        this.endPrice = form.getEndPrice();
        this.endTime = form.getEndTime();
    }


    public boolean hasBid() {
        return !bids.isEmpty();
    }
}
