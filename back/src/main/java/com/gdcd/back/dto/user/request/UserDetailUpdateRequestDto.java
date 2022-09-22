package com.gdcd.back.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public class UserDetailUpdateRequestDto {
    @Nullable
    private String profile;
    @Nullable
    private String nickname;
}
