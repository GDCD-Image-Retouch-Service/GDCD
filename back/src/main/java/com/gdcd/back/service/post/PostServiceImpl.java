package com.gdcd.back.service.post;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.domain.post.Post;
import com.gdcd.back.domain.post.PostRepository;
import com.gdcd.back.domain.post.report.ReportRepository;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.dto.post.response.PostDetailResponseDto;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import com.gdcd.back.dto.post.response.PostListByUserIdResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ReportRepository reportRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public List<PostListResponseDto> findPosts(String token) throws Exception{
        User user = findUserByEmail(decodeToken(token));
        List<Post> documentList = postRepository.findAll();
        List<PostListResponseDto> list = new ArrayList<>();
        for (Post post : documentList) {
            if (validPost(post)){
                Boolean scrap = scrapPost(post, user);
                Boolean like = likePost(post, user);
                ImageDetailResponseDto res = post.getImages().get(post.getRepresentative());
                list.add(new PostListResponseDto(post, res, scrap, like));
            }
        }
        Collections.reverse(list);
        return list;
    }

    public List<PostListResponseDto> findPostsByUser(String token, Long userId) throws Exception{
        User user = findUserByEmail(decodeToken(token));
        if (userId == null){
            List<Post> documentList = postRepository.findAllByWriterNo(user.getId());
            List<PostListResponseDto> list = new ArrayList<>();
            for (Post post : documentList) {
                if (validPost(post)){
                    Boolean scrap = scrapPost(post, user);
                    Boolean like = likePost(post, user);
                    ImageDetailResponseDto res = post.getImages().get(post.getRepresentative());
                    list.add(new PostListResponseDto(post, res, scrap, like));
                }
            }
            return list;
        }else {
            List<Post> documentList = postRepository.findAllByWriterNo(userId);
            List<PostListResponseDto> list = new ArrayList<>();
            for (Post post : documentList){
                if (validPost(post)){
                    Boolean scrap = scrapPost(post, user);
                    Boolean like = likePost(post, user);
                    ImageDetailResponseDto res = post.getImages().get(post.getRepresentative());
                    list.add(new PostListResponseDto(post, res, scrap, like));
                }
            }
            return list;
        }
    }

    public PostDetailResponseDto findPostById(String token, Long postId) throws Exception{
        User user = findUserByEmail(decodeToken(token));
        Post post = findPost(postId);
        if (validPost(post)){
            Boolean scrap = scrapPost(post, user);
            Boolean like = likePost(post, user);
            return new PostDetailResponseDto(post, scrap, like); // list(post)
        }else {
            return null;
        }
    }
    public Long addPost(String token, PostCreateRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        requestDto.setWriteNo(user.getId());
        requestDto.setWriterNickname(user.getNickname());
        requestDto.setWriterProfile(user.getProfile());
        List<ImageDetailResponseDto> images = new ArrayList<>();
        for (Long id : requestDto.getImages()){
            images.add(new ImageDetailResponseDto(findImage(id)));
        }
        requestDto.setImageList(images);
        user.addPostCount();
        userRepository.save(user);
        return postRepository.save(requestDto.toDocument()).getId();

    };

    public Long modifyPost(String token, PostUpdateRequestDto requestDto){
        Post post = findPost(requestDto.getPostId());
        if (validPost(post)){
            post.setUpdateTime(LocalDateTime.now());
            List<ImageDetailResponseDto> images = new ArrayList<>();
            for (Long id : requestDto.getImages()){
                images.add(new ImageDetailResponseDto(findImage(id)));
            }
            post.update(
                    requestDto.getTitle(),
                    requestDto.getContent(),
                    requestDto.getPrivacyBound(),
                    requestDto.getRepresentative(),
                    images
            );
            return postRepository.save(post).getId();
        }else {
            return null;
        }
    }

    public String removePost(String token, Long postId) throws Exception {
        Post post = findPost(postId);
        User user = findUserByEmail(decodeToken(token));
        if (validPost(post)){
            user.subPostCount();
            userRepository.save(user);
            post.setValidation(false);
            post.subLikeCount();
            postRepository.save(post);
            List<Long> scrapUserList = post.getScrapUsers();
            for (Long id : scrapUserList) {
                User scrapper = userRepository.findById(id).get();
                scrapper.cancelScrap(postId); // scrapPosts & scrapCount
                userRepository.save(scrapper);
            }
            post.setScrapUsers(new ArrayList<>());

            List<Long> likeUserList = post.getLikeUsers();
            for (Long id : likeUserList) {
                User liker = userRepository.findById(id).get();
                liker.cancelLike(postId);
                userRepository.save(liker);
            }
            post.setLikeUsers(new ArrayList<>());

            // report는 급하지 않으니까 나중에
            return "성공적으로 삭제되었습니다.";
        }else {
            return "존재하지 않는 게시글입니다";
        }
    }

    public Long reportPost(String token, PostReportRequestDto requestDto) throws Exception{
        User user = findUserByEmail(decodeToken(token));
        user.addDailyReports();
        userRepository.save(user);
        return reportRepository.save(requestDto.toDocument(user.getId())).getId();
    }
    public Long likePost(String token, Long postId) throws Exception {
        Post post = findPost(postId);
        User user = findUserByEmail(decodeToken(token));
        if (validPost(post)){
            if (!post.getLikeUsers().contains(user.getId())){
                // likeUser(Post)
                List<Long> likeUser = post.getLikeUsers();
                likeUser.add(user.getId());
                post.setLikeUsers(likeUser);
                post.addLikeCount();

//                likePost(User)
                List<Long> likePost = user.getLikePosts();
                likePost.add(postId);
                user.setLikePosts(likePost);
                user.addLikeCount();

                postRepository.save(post);
                userRepository.save(user);
            } else {
                List<Long> likeuser = post.getLikeUsers();
                likeuser.remove(user.getId());
                post.setLikeUsers(likeuser);
                post.subLikeCount();

                List<Long> likePost = user.getLikePosts();
                likePost.remove(postId);
                user.setLikePosts(likePost);
                user.subLikeCount();

                userRepository.save(user);
                postRepository.save(post);
            }
            return postId;
        }else {
            return null;
        }
    }

    public Long scrapPost(String token, Long postId) throws Exception{
        Post post = findPost(postId);
        User user = findUserByEmail(decodeToken(token));
        if (validPost(post)){
            if (!post.getScrapUsers().contains(user.getId())){
                List<Long> scrapUser = post.getScrapUsers();
                scrapUser.add(user.getId());
                post.setScrapUsers(scrapUser);

                List<Long> scrapPost = user.getScrapPosts();
                scrapPost.add(postId);
                user.setScrapPosts(scrapPost);
                user.addScrapCount();

                postRepository.save(post);
                userRepository.save(user);
            } else {
                List<Long> scrapUsers = post.getScrapUsers();
                scrapUsers.remove(user.getId());
                post.setScrapUsers(scrapUsers);

                List<Long> scrapPost = user.getScrapPosts();
                scrapPost.remove(postId);
                user.setScrapPosts(scrapPost);
                user.subScrapCount();

                userRepository.save(user);
                postRepository.save(post);
            }
            return postId;
        }else {
            return null;
        }
    }



//.


    private Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    private Image findImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException(imageId + "번은(는) 존재하지 않는 게시글입니다."));
    }
//    private Boolean validPost(Post post){
//        if (post.getValidation().equals(true)){
//            return true;
//        }else {
//            return false;
//        }
//    }

//    private List<ImageSimpleResponseDto> list(Post post){
//        List<ImageSimpleResponseDto> result = new ArrayList<>();
//        List<String> list = post.getImages();
//        for(String url : list){
//            result.add(ImageSimpleResponseDto.builder()
//                    .imageUrl(url)
//                    .rank(1)
//                    .build());
//        }
//        return result;
//    }
    // 찐
//    private List<ImageSimpleResponseDto> list(Post post){
//        List<ImageSimpleResponseDto> result = new ArrayList<>();
//        List<String> list = post.getImages();
//        for(String _id : list){
//            Image image = findImage(_id);
//            result.add(ImageSimpleResponseDto.builder()
//                    .imageUrl(image.getImgUrl())
////                            .rank()
//                    .build());
//        }
//        return result;
//    }
    private User findUserByEmail(String email) throws Exception {
        if (userRepository.findByEmail(email).isPresent())
            return userRepository.findByEmail(email).get();
        else
            throw new Exception("User Not Found");
    }

    private boolean validPost(Post post) {
        return post.getValidation();
    }

    private boolean scrapPost(Post post, User user){
        if (post.getScrapUsers().contains(user.getId())){
            return true;
        }else {
            return false;
        }
    }

    private boolean likePost(Post post, User user){
        if (post.getLikeUsers().contains(user.getId())){
            return true;
        }else {
            return false;
        }
    }

    public String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }

}
