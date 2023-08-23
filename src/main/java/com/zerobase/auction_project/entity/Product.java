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
public class Product {

    @Id
    private Long Id;

    private String title;
    private String image;
    private Long startPrice;
    private Date createdTime;
    private Date updateTime;
    private boolean status;
}
