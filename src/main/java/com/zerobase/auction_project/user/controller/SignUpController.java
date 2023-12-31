//package com.zerobase.auction_project.controller;
//
//
//import com.zerobase.auction_project.application.SignUpApplication;
//import com.zerobase.auction_project.domain.request.UserSignUpForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/signup")
//public class SignUpController {
//
//    private final SignUpApplication signUpApplication;
//
//    //회원 가입
//    @PostMapping
//    public ResponseEntity<String> signup(@RequestBody UserSignUpForm form) {
//        return ResponseEntity.ok(signUpApplication.signUp(form));
//    }
//
//    //이메일 인증번호 인증
//    @GetMapping("/verify")
//    public ResponseEntity<String> verify(@RequestParam String email, String authKey) {
//        signUpApplication.verifyEmail(email, authKey);
//        return ResponseEntity.ok("인증이 완료되었습니다.");
//    }
//
//
//}