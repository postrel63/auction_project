package com.zerobase.auction_project.controller;

import com.zerobase.auction_project.application.SignUpApplication;
import com.zerobase.auction_project.domain.dto.UserSignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final SignUpApplication signUpApplication;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignUpForm form, HttpSession session) {
        return ResponseEntity.ok(signUpApplication.signUp(form, session));
    }

    //이메일 인증번호 인증
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String email, HttpSession session) {
        signUpApplication.verifyEmail(email, session);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

}
