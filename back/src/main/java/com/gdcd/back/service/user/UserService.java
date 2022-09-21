package com.gdcd.back.service.user;

import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

public interface UserService {
    public Map<String, String> loginUser(UserCreateRequestDto requestDto);
    public Map<String, Object> checkNickname(String nickname);
    public Map<String, Object> findUser(String token, Long userId);
    public Map<String, Object> modifyUser(String token, UserDetailUpdateRequestDto requestDto);
    public Map<String, Object> removeUser(String token);
    public Map<String, String> blockUser(String token, Long userId);
    public Map<String, Object> findScraps(String token);
    public Map<String, Object> findLikes(String token);
    public Map<String, String> followUser(String token, Long userId);
    public Map<String, Object> findFollowers(String token);
    public Map<String, Object> findFollowings(String token);
}
