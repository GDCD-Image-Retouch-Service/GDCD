package com.gdcd.back.service.user;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.domain.user.block.Block;
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
    private Map<String, String> RESULT_STRING;
    private Map<String, Object> RESULT_OBJECT;

    @Override
    public Map<String, String> loginUser(UserCreateRequestDto requestDto) {
        RESULT_STRING = new HashMap<>();
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
        RESULT_OBJECT = new HashMap<>();
        RESULT_OBJECT.put("usable", !userRepository.findByNickname(nickname).isPresent());
        return RESULT_OBJECT;
    }

    public Map<String, Object> findUser(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
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
        RESULT_OBJECT = new HashMap<>();
        try {
            User user = findUserByEmail(decodeToken(token));
            user.update(requestDto);
            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
            // 유저 정보를 바꾸면 post가 가지고 있는 writer 정보 또한 바뀌어야함.
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "USER NOT UPDATED");
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> removeUser(String token) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User user = findUserByEmail(decodeToken(token));
            user.setValidation(false);
            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "USER NOT DELETED");
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> blockUser(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            String blockerEmail = decodeToken(token);
            User blocking = findUserById(userId);
            Block block = Block.builder()
                    .blocker(findUserByEmail(blockerEmail).getId())
                    .blocking(userId)
                    .blockingNickname(blocking.getNickname())
                    .blockingProfile(blocking.getProfile())
                    .build();
            RESULT_OBJECT.put("block", blockRepository.save(block).getId());
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "USER NOT BLOCKED");
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> cancleBlock(Long blockId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            blockRepository.delete(findBlockById(blockId));
            RESULT_OBJECT.put("unblock", blockId);
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "USER NOT UNBLOCKED");
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> findScraps(String token) {
        RESULT_OBJECT = new HashMap<>();
        return RESULT_OBJECT;
    }

    public Map<String, Object> findLikes(String token) {
        RESULT_OBJECT = new HashMap<>();
        return RESULT_OBJECT;
    }

    public Map<String, String> followUser(String token, Long userId) {
        RESULT_STRING = new HashMap<>();
        return RESULT_STRING;
    }

    public Map<String, Object> findFollowers(String token) {
        RESULT_OBJECT = new HashMap<>();
        return RESULT_OBJECT;
    }

    public Map<String, Object> findFollowings(String token) {
        RESULT_OBJECT = new HashMap<>();
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

    private Block findBlockById(Long blockId) throws Exception {
        if (blockRepository.findById(blockId).isPresent())
            return blockRepository.findById(blockId).get();
        else
            throw new Exception("User Not Blocked");
    }

    private boolean validUser(User user) {
        return user.getValidation();
    }

    public String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }
}