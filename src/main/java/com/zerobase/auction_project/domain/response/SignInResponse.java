package com.zerobase.auction_project.domain.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {
    private String token;
    private String name;
    private String email;
}
