package com.gdcd.back.service.user;

import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    public String loginUser(UserCreateRequestDto requestDto);
    public String addUser(UserCreateRequestDto requestDto);
    public UserDetails findUserForToken(String email) throws UsernameNotFoundException;
    public Boolean checkNickname(String nickname);
//    public Object checkuserId(Object userId);
    UserDetailResponseDto findUserById(Long userId);
    public UserDetailResponseDto modifyUser(Long userId, UserDetailUpdateRequestDto requestDto);
    public String removeUser(Long userId);
}
