package com.zerobase.auction_project.service;

import com.zerobase.auction_project.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    //회원가입
    User save(User user);

    boolean isExistEmail(String email);

    String encryptPassword(String password);

    User createUser(String email, String password, String authKey, String phone, String birth);

    void verifyEmail(String email, String authKey);

    //로그인
    User userSignInValid(String email, String password);

    Optional<User> findByIdAndEmail(Long id, String email);



}
