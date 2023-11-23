package com.zerobase.auction_project.application;

import com.zerobase.auction_project.components.MailComponents;
import com.zerobase.auction_project.user.domain.User;
import com.zerobase.auction_project.user.domain.request.UserSignUpForm;
import com.zerobase.auction_project.exception.custom.CustomException;
import com.zerobase.auction_project.exception.code.ErrorCode;
import com.zerobase.auction_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final UserService userService;
    private final MailComponents mailComponents;

    public String signUp(@Validated UserSignUpForm form) {
        if (userService.isExistEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }
        String encPassword = userService.encryptPassword(form.getPassword());
        String authKey = UUID.randomUUID().toString();


        User user = userService.createUser(
                form.getEmail(), encPassword, authKey, form.getPhone(), form.getBirth());
        userService.save(user);

        String text = null;
        try {
            text = "<p>회원가입을 축하드립니다</p><p>링크를 클릭해서 회원가입을 완료하세요</p>"
                    + "<div><a href='http://localhost:8080/api/users/verify?email="
                    + URLEncoder.encode(user.getEmail(), "UTF-8") + "&authKey=" + authKey + "'>Click here to verify</a></div>";
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        mailComponents.sendMail(user.getEmail(), "중고경매 플랫폼 회원가입을 축하드립니다!", text);

        return "회원가입 성공";
    }

    //이메일 인증
    public void verifyEmail(String email, String authKey) {
        userService.verifyEmail(email, authKey);
    }

}
