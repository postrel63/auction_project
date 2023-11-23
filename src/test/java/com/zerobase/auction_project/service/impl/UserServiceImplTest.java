package com.zerobase.auction_project.service.impl;

import com.zerobase.auction_project.user.domain.User;
import com.zerobase.auction_project.user.repository.UserRepository;
import com.zerobase.auction_project.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    @DisplayName("회원가입")
    void SignUp() {
        //given
        String email = "test@test.com";
        String password = "1111";
        String authKey = "1111";
        String phone = "01011111111";
        String birth = "birth";


        User user = userService.createUser(email, password, authKey, phone, birth);
        Assertions.assertEquals(user.getEmail(), email);

    }

    @Test
    void verifyTest() {
        //given
        //when
        //then
    }


}