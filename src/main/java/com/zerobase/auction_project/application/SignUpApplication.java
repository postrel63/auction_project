package com.zerobase.auction_project.application;

import com.zerobase.auction_project.components.MailComponents;
import com.zerobase.auction_project.domain.User;
import com.zerobase.auction_project.domain.dto.UserSignUpForm;
import com.zerobase.auction_project.exception.CustomException;
import com.zerobase.auction_project.exception.ErrorCode;
import com.zerobase.auction_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final UserService userService;
    private final MailComponents mailComponents;

    public String signUp(@Validated UserSignUpForm form, HttpSession session) {
        if (userService.isExistEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }
        String encPassword = userService.encryptPassword(form.getPassword());
        String authKey = UUID.randomUUID().toString();
        //인증번호는 세션에 담아서 보내기 -> 캐시로?
        session.setAttribute(form.getEmail(), authKey);

        User user = userService.createUser(
                form.getEmail(), encPassword, authKey, form.getPhone(), form.getBirth());
        userService.save(user);

        String text = null;
        try {
            text = "<p>회원가입을 축하드립니다</p><p>링크를 클릭해서 회원가입을 완료하세요</p>"
                    + "<div><a href='http://localhost:8080/api/users/verify?email="
                    + URLEncoder.encode(user.getEmail(), "UTF-8") + "'>Click here to verify</a></div>";
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        mailComponents.sendMail(user.getEmail(), "중고경매 플랫폼 회원가입을 축하드립니다!", text);

        return "회원가입 성공";
    }

    //이메일 인증
    public void verifyEmail(String email, HttpSession session) {
        userService.verifyEmail(email, session);
    }

}
