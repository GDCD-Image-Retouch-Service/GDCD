package com.gdcd.back.dto.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@Builder
public class UserDetailUpdateRequestDto {
    @Nullable
    private String profile;
    @Nullable
    private String nickname;
}
