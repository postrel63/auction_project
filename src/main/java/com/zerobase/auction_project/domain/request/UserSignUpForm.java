package com.zerobase.auction_project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class UserSignUpForm {

    private String email;
    private String name;
    private String password;
    private String birth;
    private String phone;


}
