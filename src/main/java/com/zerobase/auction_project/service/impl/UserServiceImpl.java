package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.domain.User;
import com.zerobase.auction_project.exception.CustomException;
import com.zerobase.auction_project.exception.ErrorCode;
import com.zerobase.auction_project.repository.UserRepository;
import com.zerobase.auction_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

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

    //회원가입 시 이메일 인증
    @Override
    @Transactional
    public void verifyEmail(String email, String authKey) {

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

    //로그인 시 유효성 검사
    @Override
    public User userSignInValid(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        User user = optionalUser.get();
        if (!BCrypt.checkpw(password, user.getUserPassword())) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        return user;
    }

    @Override
    public Optional<User> findByIdAndEmail(Long id, String email) {
        return userRepository.findById(id).stream().filter(
                user -> user.getEmail().equals(email)
        ).findFirst();


    }
}






