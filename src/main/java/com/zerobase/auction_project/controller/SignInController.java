//package com.zerobase.auction_project.controller;
//
//import com.zerobase.auction_project.application.SignInApplication;
//import com.zerobase.auction_project.application.SignUpApplication;
//import com.zerobase.auction_project.domain.dto.SignInResponse;
//import com.zerobase.auction_project.domain.dto.UserSignInForm;
//import com.zerobase.auction_project.domain.dto.UserSignUpForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/signin")
//public class SignInController {
//
//    private final SignInApplication signInApplication;
//
//    //로그인
//    @PostMapping
//    public ResponseEntity<SignInResponse> userSignIn(@RequestBody UserSignInForm form){
//
//        SignInResponse signInResponse = signInApplication.signInToken(form.getEmail(), form.getPassword());
//        return ResponseEntity.ok(signInResponse);
//    }
//
//}
