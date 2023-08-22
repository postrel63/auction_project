package com.zerobase.auction_project.entity;


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

    private String userId;
    private String buyerId;
    private Long productId;
    private Long price;
    private Date createTime;
    private Date startTime;
    private Date endTime;

}
