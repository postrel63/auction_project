package com.zerobase.auction_project.auction.domain;


import com.zerobase.auction_project.bid.domain.Bid;
import com.zerobase.auction_project.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Auction.bids", attributeNodes = @NamedAttributeNode("bids"))
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    //등록할 때
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
                .status(ProductStatus.Pending)
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

    //입찰기록 유무
    public boolean hasBid() {
        return !bids.isEmpty();
    }

    //최고 입찰가
    public Integer getHighestBidPrice(){
        return bids.stream().mapToInt(Bid::getPrice)
                .max().orElse(0);
    }


}
