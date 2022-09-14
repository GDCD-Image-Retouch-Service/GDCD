package com.gdcd.back.service.user;

import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;

public interface UserService {
    public Boolean checkNickname(String nickname);
//    public Object checkuserId(Object userId);
    UserDetailResponseDto findUserById(Long userId);
    public UserDetailResponseDto modifyUser(Long userId, UserDetailUpdateRequestDto requestDto);

    public String removeUser(Long userId);
}
