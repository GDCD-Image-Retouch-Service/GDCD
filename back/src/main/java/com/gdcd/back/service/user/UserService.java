package com.gdcd.back.service.user;

import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {
    public Map<String, String> loginUser(UserCreateRequestDto requestDto);
    public Map<String, Object> checkNickname(String nickname);
    public Map<String, Object> findUser(String token, Long userId);
//    public Map<String, Object> modifyUser(String token, UserDetailUpdateRequestDto requestDto);
    public Map<String, Object> modifyUser(String token, MultipartFile profile, String nickname);
    public Map<String, Object> removeUser(String token);
    public Map<String, Object> blockUser(String token, Long userId);
//    public Map<String, Object> cancleBlock(Long blockId);
    public Map<String, Object> findScraps(String token);
    public Map<String, Object> findLikes(String token);
    public Map<String, Object> followUser(String token, Long userId);
    public Map<String, Object> findFollowers(String token, Long userId);
    public Map<String, Object> findFollowings(String token, Long userId);
}
