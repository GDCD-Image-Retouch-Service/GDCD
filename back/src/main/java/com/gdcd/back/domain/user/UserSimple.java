package com.gdcd.back.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSimple {
    private Long id;
    private String nickname;
    private String profile;
    private boolean validation;
}
