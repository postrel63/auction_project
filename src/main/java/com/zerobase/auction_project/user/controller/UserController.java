package com.zerobase.auction_project.user.controller;

import com.zerobase.auction_project.application.SignInApplication;
import com.zerobase.auction_project.application.SignUpApplication;
import com.zerobase.auction_project.user.domain.response.SignInResponse;
import com.zerobase.auction_project.user.domain.request.UserSignInForm;
import com.zerobase.auction_project.user.domain.request.UserSignUpForm;
import com.zerobase.auction_project.jwt.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final SignUpApplication signUpApplication;
    private final SignInApplication signInApplication;
    private final JwtAuthenticationProvider provider;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignUpForm form) {
        return ResponseEntity.ok(signUpApplication.signUp(form));
    }

    //이메일 인증번호 인증
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String email, String authKey) {
        signUpApplication.verifyEmail(email, authKey);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

    //로그인
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> userSignIn(@RequestBody UserSignInForm form){

        SignInResponse signInResponse = signInApplication.signInToken(form.getEmail(), form.getPassword());
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/valid")
    public boolean valid(@RequestHeader(name = "X-AUTH-TOKEN") String token){
        return provider.validateToken(token);
    }

}
