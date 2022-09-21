package com.gdcd.back.service.user;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.domain.user.block.BlockRepository;
import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BlockRepository blockRepository;
    private final Map<String, String> RESULT_STRING = new HashMap<>();
    private final Map<String, Object> RESULT_OBJECT = new HashMap<>();

    @Override
    public Map<String, String> loginUser(UserCreateRequestDto requestDto) {
        String email = requestDto.getEmail();
        try {
            if (!validUser(findUserByEmail(email))) {
                RESULT_STRING.put("error", "USER NOT VALID");
                return RESULT_STRING;
            }
        } catch (Exception e) {
            userRepository.save(requestDto.toDocument());
        }
        RESULT_STRING.put("token", jwtTokenProvider.createToken(email));
        return RESULT_STRING;
    }

    public Map<String, Object> checkNickname(String nickname) {
        RESULT_OBJECT.put("usable", !userRepository.findByNickname(nickname).isPresent());
        return RESULT_OBJECT;
    }

    public Map<String, Object> findUser(String token, Long userId) {
        try {
            User user;
            if (userId == null)
                user = findUserByEmail(decodeToken(token));
            else
                user = findUserById(userId);
            if (!validUser(user))
                RESULT_OBJECT.put("error", "USER NOT VALID");
            else
                RESULT_OBJECT.put("user", new UserDetailResponseDto(user));
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "USER NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> modifyUser(String token, UserDetailUpdateRequestDto requestDto) {
        try {
            User user = findUserByEmail(decodeToken(token));
            user.update(requestDto);
            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
            // 유저 정보를 바꾸면 post가 가지고 있는 writer 정보 또한 바뀌어야함.
        } catch(Exception e) {
            RESULT_OBJECT.put("error", "USER NOT UPDATED");
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> removeUser(String token) {
        try {
            User user = findUserByEmail(decodeToken(token));
            user.setValidation(false);
            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
        } catch(Exception e) {
            RESULT_OBJECT.put("error", "USER NOT DELETED");
        }
        return RESULT_OBJECT;
    }

    public Map<String, String> blockUser(String token, Long userId) {
        return RESULT_STRING;
    }

    public Map<String, Object> findScraps(String token) {
        return RESULT_OBJECT;
    }

    public Map<String, Object> findLikes(String token) {
        return RESULT_OBJECT;
    }

    public Map<String, String> followUser(String token, Long userId) {
        return RESULT_STRING;
    }

    public Map<String, Object> findFollowers(String token) {
        return RESULT_OBJECT;
    }

    public Map<String, Object> findFollowings(String token) {
        return RESULT_OBJECT;
    }

    private User findUserById(Long userId) throws Exception {
        if (userRepository.findById(userId).isPresent())
            return userRepository.findById(userId).get();
        else
            throw new Exception("User Not Found");
    }

    private User findUserByEmail(String email) throws Exception {
        if (userRepository.findByEmail(email).isPresent())
            return userRepository.findByEmail(email).get();
        else
            throw new Exception("User Not Found");
    }

    private boolean validUser(User user) {
        return user.getValidation();
    }

    public String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }
}