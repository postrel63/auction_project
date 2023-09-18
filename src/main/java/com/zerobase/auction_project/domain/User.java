package com.zerobase.auction_project.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userPassword;
    private String email;
    private String phone;
    private String birth;
    private String name;
    private String image;
    private boolean auth; // 인증상태
    private String authKey; // 인증번호
    private LocalDateTime authExpiredAt; //인증번호 만료 시간


}
