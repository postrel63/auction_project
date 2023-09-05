package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.domain.User;
import com.zerobase.auction_project.exception.CustomException;
import com.zerobase.auction_project.exception.ErrorCode;
import com.zerobase.auction_project.repository.UserRepository;
import com.zerobase.auction_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isExistEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

    @Override
    public User createUser(String email, String password, String authKey, String phone, String birth) {
        return User.builder()
                .userPassword(password)
                .email(email)
                .phone(phone)
                .birth(birth)
                .authKey(authKey)
                .authExpiredAt(LocalDateTime.now().plusDays(1))
                .auth(false)
                .build();
    }

    @Override
    @Transactional
    public void verifyEmail(String email, HttpSession session) {

        String authKey = (String) session.getAttribute(email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (user.isAuth()) {
            throw new CustomException(ErrorCode.ALREADY_VERIFY);
        }
        if (!user.getAuthKey().equals(authKey)) {
            throw new CustomException(ErrorCode.WRONG_VERIFY);
        }
        if (user.getAuthExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.EXPIRED_VERIFY);
        }

        user.setAuth(true);

    }
}






