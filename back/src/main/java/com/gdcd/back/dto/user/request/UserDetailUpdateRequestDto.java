package com.gdcd.back.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDetailUpdateRequestDto {
    private String profile;
    private String nickname;
}
