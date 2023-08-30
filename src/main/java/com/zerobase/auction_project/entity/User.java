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
public class User {

    @Id
    private Long id;
    private String userPassword;
    private String email;
    private String nickName;
    private String image;
    private String auth;
    private String authKey;
}
