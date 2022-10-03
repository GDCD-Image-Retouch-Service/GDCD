package com.gdcd.back.dto.user.response;

import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserSimple;
import lombok.Getter;

@Getter
public class FollowListResponseDto {
    private Long userId;
    private String nickname;
    private String profile;

    public FollowListResponseDto(UserSimple document) {
        this.userId = document.getId();
        this.nickname = document.getNickname();
        this.profile = document.getProfile();
    }
}
