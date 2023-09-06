package com.zerobase.auction_project.application;

import com.zerobase.auction_project.domain.User;
import com.zerobase.auction_project.domain.dto.SignInResponse;
import com.zerobase.auction_project.jwt.common.UserType;
import com.zerobase.auction_project.jwt.config.JwtAuthenticationProvider;
import com.zerobase.auction_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final UserService userService;
    private final JwtAuthenticationProvider provider;

    //로그인 -> JWT 토큰 발행 -> 기본정보(이름,이메일)와 함께 반환
    public SignInResponse signInToken(String email, String password) {

        User user = userService.userSignInValid(email, password);
        String token = provider.createToken(user.getEmail(), user.getId(), UserType.USER);

        return SignInResponse.builder()
                .token(token)
                .name(user.getName())
                .email(user.getEmail())
                .build();

    }
}