package com.gdcd.back.service.user;

import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {
    public Map<String, String> loginUser(UserCreateRequestDto requestDto);
    public Map<String, Object> checkNickname(String nickname);
    public Map<String, Object> findUser(String token, Long userId);
    public Map<String, Object> modifyProfile(String token, MultipartFile profile);
    public Map<String, Object> modifyNickname(String token, String nickname);
    public Map<String, Object> removeUser(String token);
    public Map<String, Object> blockUser(String token, Long userId);
    public Map<String, Object> findScraps(String token, Long userId, Pageable pageable);
    public Map<String, Object> findLikes(String token, Long userId, Pageable pageable);
    public Map<String, Object> followUser(String token, Long userId);
    public Map<String, Object> followCheck(String token, Long userId);
    public Map<String, Object> findFollowers(String token, Long userId, Pageable pageable);
    public Map<String, Object> findFollowings(String token, Long userId, Pageable pageable);
    public byte[] findProfile(String from);
}
