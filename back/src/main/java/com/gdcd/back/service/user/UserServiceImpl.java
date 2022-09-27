package com.gdcd.back.service.user;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.domain.user.block.Block;
import com.gdcd.back.domain.user.block.BlockRepository;
import com.gdcd.back.domain.user.follow.Follow;
import com.gdcd.back.domain.user.follow.FollowRepository;
import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.FollowListResponseDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BlockRepository blockRepository;
    private final FollowRepository followRepository;
    private Map<String, String> RESULT_STRING;
    private Map<String, Object> RESULT_OBJECT;
    private final String ROOT = "/app/data/profiles/";
//    private final String ROOT = "C:/SSAFY/AI/profiles/";
    private final String DEFAULT_PATH = ROOT + "default.jpeg";
    private final String PROFILE_REQUEST_URI = "http://localhost:8081/api/user/profile?from=";

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
            userRepository.save(requestDto.toDocument(PROFILE_REQUEST_URI, DEFAULT_PATH));
        }
        RESULT_STRING.put("token", jwtTokenProvider.createToken(email));
        return RESULT_STRING;
    }
    @Override
    public Map<String, Object> checkNickname(String nickname) {
        RESULT_OBJECT = new HashMap<>();
        RESULT_OBJECT.put("usable", !userRepository.findByNickname(nickname).isPresent());
        return RESULT_OBJECT;
    }

    @Override
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

    @Override
    public Map<String, Object> modifyUser(String token, MultipartFile profile, String nickname) {
        RESULT_OBJECT = new HashMap<>();
        try {
//        	System.out.println();
//        	System.out.println(profile.getOriginalFilename());
        	System.out.println(nickname);
            User user = findUserByEmail(decodeToken(token));
            String filePath = DEFAULT_PATH;
            if (profile != null) {
                String type = profile.getContentType();
                String endpoint = "." + type.substring(type.lastIndexOf("/") + 1);
                filePath = ROOT + user.getId().toString() + endpoint;
                profile.transferTo(new File(filePath));
            }
            user.update(PROFILE_REQUEST_URI, filePath, nickname);
            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
            // fix) 유저 정보를 바꾸면 post가 가지고 있는 writer 정보 또한 바뀌어야함.
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "USER NOT UPDATED");
        }
        return RESULT_OBJECT;
    }

    @Override
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

    @Override
    public Map<String, Object> blockUser(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            String blocker = decodeToken(token);
            User blocking = findUserById(userId);
            Block block;
            if (blockRepository.findByBlockerAndBlocking(blocker, blocking).isPresent()) {
                blockRepository.deleteByBlockerAndBlocking(blocker, blocking);
                RESULT_OBJECT.put("unblock", userId);
            } else {
                block = Block.builder()
                        .blocker(blocker)
                        .blocking(blocking)
                        .build();
                RESULT_OBJECT.put("block", blockRepository.save(block).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "USER NOT BLOCKED");
        }
        return RESULT_OBJECT;
    }

//    public Map<String, Object> cancleBlock(Long blockId) {
//        RESULT_OBJECT = new HashMap<>();
//        try {
//            blockRepository.delete(findBlockById(blockId));
//            RESULT_OBJECT.put("unblock", blockId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            RESULT_OBJECT.put("error", "USER NOT UNBLOCKED");
//        }
//        return RESULT_OBJECT;
//    }

    @Override
    public Map<String, Object> findScraps(String token) {
        RESULT_OBJECT = new HashMap<>();
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findLikes(String token) {
        RESULT_OBJECT = new HashMap<>();
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> followUser(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User follower = findUserByEmail(decodeToken(token));
            User following = findUserById(userId);
            Follow follow;
            if (followRepository.findByFollowerAndFollowing(follower, following).isPresent()) {
                followRepository.deleteByFollowerAndFollowing(follower, following);
                RESULT_OBJECT.put("unfollow", userId);
                // fix) unFollow할 때는, user의 followCount--
            } else {
                follow = Follow.builder()
                        .follower(follower)
                        .following(following)
                        .build();
                RESULT_OBJECT.put("follow", followRepository.save(follow).getId());
                // fix) Follow할 때는, user의 followCount++
            }
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "FOLLOW NOT ALLOWED");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findFollowers(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User following;
            if (userId == null)
                following = findUserByEmail(decodeToken(token));
            else
                following = findUserById(userId);
            List<Follow> documentList = followRepository.findAllByFollowing(following);
            List<FollowListResponseDto> list = new ArrayList<>();
            for (Follow follow : documentList) {
                list.add(new FollowListResponseDto(follow.getFollower()));
            }
            RESULT_OBJECT.put("followers", list);
            RESULT_OBJECT.put("followerCount", list.size());
            // fix) 그냥 유저의 followCount field 참조하도록 수정
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "USER NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findFollowings(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User follower;
            if (userId == null)
                follower = findUserByEmail(decodeToken(token));
            else
                follower = findUserById(userId);
            List<Follow> documentList = followRepository.findAllByFollower(follower);
            List<FollowListResponseDto> list = new ArrayList<>();
            for (Follow follow : documentList) {
                list.add(new FollowListResponseDto(follow.getFollowing()));
            }
            RESULT_OBJECT.put("followings", list);
            RESULT_OBJECT.put("followingCount", list.size());
            // fix) user의 followCount field 참조하도록 수정
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "USER NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public byte[] findProfile(String from) {
        try {
            return IOUtils.toByteArray(Files.newInputStream(Paths.get(from)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

//    private Block findBlockById(Long blockId) throws Exception {
//        if (blockRepository.findById(blockId).isPresent())
//            return blockRepository.findById(blockId).get();
//        else
//            throw new Exception("User Not Blocked");
//    }

    private boolean validUser(User user) {
        return user.getValidation();
    }

    public String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }
}