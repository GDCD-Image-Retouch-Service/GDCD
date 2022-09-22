package com.gdcd.back.dto.user.request;

import com.gdcd.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String email;
    private String nickname;
    public User toDocument() {
        return User.builder()
                .email(email)
                .nickname(nickname) // default nickname creator
                .profile("") // default profile url
                .build();
    }
}
