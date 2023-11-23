package com.zerobase.auction_project.user.domain.dto;

import com.zerobase.auction_project.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String phone;
    private String birth;
    private String name;
    private String image;

    public static UserDto from(User user){
        return new UserDto(user.getId(), user.getEmail(), user.getPhone(), user.getBirth(), user.getName(), user.getImage());
    }

}
