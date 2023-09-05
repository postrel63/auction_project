package com.zerobase.auction_project.service;

import com.zerobase.auction_project.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface UserService {

    User save(User user);

    boolean isExistEmail(String email);

    String encryptPassword(String password);

    User createUser(String email, String password, String authKey, String phone, String birth);

    void verifyEmail(String email, HttpSession session);
}
