package com.gdcd.back.dto.user.response;

import com.gdcd.back.domain.user.User;
import lombok.Getter;

@Getter
public class UserDetailResponseDto {
    private String profile;
    private String nickname;


    public UserDetailResponseDto(User document){
        this.profile = document.getProfile();
        this.nickname = document.getNickname();
    }
}
