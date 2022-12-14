package com.gdcd.back.service.user;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.comment.Comment;
import com.gdcd.back.domain.comment.CommentRepository;
import com.gdcd.back.domain.post.Post;
import com.gdcd.back.domain.post.PostRepository;
import com.gdcd.back.domain.post.report.Report;
import com.gdcd.back.domain.post.report.ReportRepository;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.domain.user.UserSimple;
import com.gdcd.back.domain.user.block.Block;
import com.gdcd.back.domain.user.block.BlockRepository;
import com.gdcd.back.domain.user.follow.Follow;
import com.gdcd.back.domain.user.follow.FollowRepository;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.response.FollowListResponseDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BlockRepository blockRepository;
    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final ReportRepository reportRepository;
    private final CommentRepository commentRepository;
    private Map<String, String> RESULT_STRING;
    private Map<String, Object> RESULT_OBJECT;
    private final String ROOT = "/app/data/profiles/";
    private final String PROFILE_REQUEST_URI = "https://j7b301.p.ssafy.io/api/user/profile?from=";
    //    private final String ROOT = "C:/SSAFY/AI/profiles/";
//    private final String PROFILE_REQUEST_URI = "http://localhost:8081/api/user/profile?from=";
    private final String DEFAULT_PATH = ROOT + "default.jpeg";

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

    public Map<String, Object> modifyProfile(String token, MultipartFile profile) {
        RESULT_OBJECT = new HashMap<>();
        try {
            if (profile == null)
                throw new Exception("PROFILE NOT UPDATED");

            User target = findUserByEmail(decodeToken(token));
            User user = findUserByEmail(decodeToken(token));
            String type = profile.getContentType();
            String endpoint = "." + type.substring(type.lastIndexOf("/") + 1);
            String filePath = ROOT + user.getId().toString() + endpoint;
            profile.transferTo(new File(filePath));
            user.updateProfile(PROFILE_REQUEST_URI, filePath);

            modifySet(target, user);

            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> modifyNickname(String token, String nickname) {
        RESULT_OBJECT = new HashMap<>();
        try {
            if (nickname == null)
                throw new Exception("NICKNAME NOT UPDATED");

            User target = findUserByEmail(decodeToken(token));
            User user = findUserByEmail(decodeToken(token));
            user.updateNickname(nickname);

            modifySet(target, user);

            RESULT_OBJECT.put("userId", userRepository.save(user).getId());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", e.getMessage());
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
            User blocker = findUserByEmail(decodeToken(token));
            User blocking = findUserById(userId);
            Block block;
            if (blockRepository.findByBlockerAndBlocking(blocker, blocking).isPresent()) {
                blockRepository.deleteByBlockerAndBlocking(blocker, blocking);
                RESULT_OBJECT.put("unblock", userId);
            } else {
                block = Block.builder()
                        .blocker(blocker.simplify())
                        .blocking(blocking.simplify())
                        .build();
                RESULT_OBJECT.put("block", blockRepository.save(block).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "USER NOT BLOCKED");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findScraps(String token, Long userId, Pageable pageable) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User user;
            if (userId != null)
                user = findUserById(userId);
            else
                user = findUserByEmail(decodeToken(token));
            List<Long> scrapList = user.getScrapPosts();
            List<PostListResponseDto> list = new ArrayList<>();
            for (Long id : scrapList) {
                Post post = postRepository.findById(id).get();
                list.add(new PostListResponseDto(
                        post,
                        post.getImages().get(post.getRepresentative()),
                        scrapPost(post, findUserByEmail(decodeToken(token))),
                        likePost(post, findUserByEmail(decodeToken(token)))
                ));
            }
            Collections.reverse(list);
            int from = pageable.getPageNumber() * pageable.getPageSize();
            int to = Math.min((pageable.getPageNumber() + 1) * pageable.getPageSize(), list.size());
            RESULT_OBJECT.put("posts", list.subList(from, to));
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "SCRAP NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findLikes(String token, Long userId, Pageable pageable) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User user;
            if (userId != null)
                user = findUserById(userId);
            else
                user = findUserByEmail(decodeToken(token));
            List<Long> likeList = user.getLikePosts();
            List<PostListResponseDto> list = new ArrayList<>();
            for (Long id : likeList) {
                Post post = postRepository.findById(id).get();
                list.add(new PostListResponseDto(
                        post,
                        post.getImages().get(post.getRepresentative()),
                        scrapPost(post, findUserByEmail(decodeToken(token))),
                        likePost(post, findUserByEmail(decodeToken(token)))
                ));
            }
            Collections.reverse(list);
            int from = pageable.getPageNumber() * pageable.getPageSize();
            int to = Math.min((pageable.getPageNumber() + 1) * pageable.getPageSize(), list.size());
            RESULT_OBJECT.put("posts", list.subList(from, to));
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "LIKE NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> followUser(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            UserSimple follower = findUserByEmail(decodeToken(token)).simplify();
            UserSimple following = findUserById(userId).simplify();

            if (follower.getId().equals(userId))
                throw new Exception("SELF-FOLLOW NOT ALLOWED");

            Follow follow;
            if (followRepository.findByFollowerAndFollowing(follower, following).isPresent()) {
                User followerUser = findUserByEmail(decodeToken(token));
                followerUser.subFollowingCount();
                userRepository.save(followerUser);
                User followingUser = findUserById(userId);
                followingUser.subFollowerCount();
                userRepository.save(followingUser);
                followRepository.deleteByFollowerAndFollowing(follower, following);
                RESULT_OBJECT.put("unfollow", userId);
            } else {
                User followerUser = findUserByEmail(decodeToken(token));
                followerUser.addFollowingCount();
                userRepository.save(followerUser);
                User followingUser = findUserById(userId);
                followingUser.addFollowerCount();
                userRepository.save(followingUser);
                follow = Follow.builder()
                        .follower(follower)
                        .following(following)
                        .build();
                RESULT_OBJECT.put("follow", followRepository.save(follow).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "FOLLOW NOT ALLOWED");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> followCheck(String token, Long userId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            UserSimple follower = findUserByEmail(decodeToken(token)).simplify();
            UserSimple following = findUserById(userId).simplify();
            RESULT_OBJECT.put("result", followRepository.findByFollowerAndFollowing(follower, following).isPresent());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "FOLLOW CHECK FAILED");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findFollowers(String token, Long userId, Pageable pageable) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User following;
            if (userId == null)
                following = findUserByEmail(decodeToken(token));
            else
                following = findUserById(userId);
            List<Follow> documentList = followRepository.findAllByFollowingOrderById(following.simplify(), pageable);
            List<FollowListResponseDto> list = new ArrayList<>();
            for (Follow follow : documentList) {
                list.add(new FollowListResponseDto(follow.getFollower()));
            }
            RESULT_OBJECT.put("followers", list);
            RESULT_OBJECT.put("followerCount", list.size());
        } catch (Exception e) {
            RESULT_OBJECT.put("error", "USER NOT FOUND");
        }
        return RESULT_OBJECT;
    }

    @Override
    public Map<String, Object> findFollowings(String token, Long userId, Pageable pageable) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User follower;
            if (userId == null)
                follower = findUserByEmail(decodeToken(token));
            else
                follower = findUserById(userId);
            List<Follow> documentList = followRepository.findAllByFollowerOrderById(follower.simplify(), pageable);
            List<FollowListResponseDto> list = new ArrayList<>();
            for (Follow follow : documentList) {
                list.add(new FollowListResponseDto(follow.getFollowing()));
            }
            RESULT_OBJECT.put("followings", list);
            RESULT_OBJECT.put("followingCount", list.size());
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

    private void modifySet(User target, User user) {
        modifyFollower(target.simplify(), user.simplify());
        modifyFollowing(target.simplify(), user.simplify());
        modifyBlocker(target, user);
        modifyBlocking(target, user);
        modifyReporter(target, user);
        modifyPostWriter(target, user);
        modifyCommentWriter(target, user);
    }

    private void modifyFollower(UserSimple target, UserSimple user) {
        List<Follow> followerList = followRepository.findAllByFollower(target);
        for (Follow follow : followerList) {
            follow.modifyFollower(user);
            followRepository.save(follow);
        }
    }

    private void modifyFollowing(UserSimple target, UserSimple user) {
        List<Follow> followingList = followRepository.findAllByFollowing(target);
        for (Follow follow : followingList) {
            follow.modifyFollowing(user);
            followRepository.save(follow);
        }
    }

    private void modifyBlocker(User target, User user) {
        List<Block> blockerList = blockRepository.findAllByBlocker(target);
        for (Block block : blockerList) {
            block.modifyBlocker(user.simplify());
            blockRepository.save(block);
        }
    }

    private void modifyBlocking(User target, User user) {
        List<Block> blockingList = blockRepository.findAllByBlocking(target);
        for (Block block : blockingList) {
            block.modifyBlocking(user.simplify());
            blockRepository.save(block);
        }
    }

    private void modifyReporter(User target, User user) {
        List<Report> reportList = reportRepository.findAllByUserId(target.getId());
        for (Report report : reportList) {
            report.modifyReporter(user);
            reportRepository.save(report);
        }
    }

    private void modifyPostWriter(User target, User user) {
        List<Post> postList = postRepository.findAllByWriterNo(target.getId());
        for (Post post : postList) {
            post.modifyWriter(user);
            postRepository.save(post);
        }
    }

    private void modifyCommentWriter(User target, User user) {
        UserSimple simpleTarget = target.simplify();
        UserSimple simpleUser = user.simplify();
        List<Comment> commentList = commentRepository.findAllByWriter(simpleTarget);
        for (Comment comment : commentList) {
            comment.modifyWriter(simpleUser);
            commentRepository.save(comment);
        }
    }

    private boolean scrapPost(Post post, User user) {
        return post.getScrapUsers().contains(user.getId());
    }

    private boolean likePost(Post post, User user) {
        return post.getLikeUsers().contains(user.getId());
    }

    private boolean validUser(User user) {
        return user.getValidation();
    }

    private String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }
}