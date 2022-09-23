package com.gdcd.back.service.post;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.domain.post.Post;
import com.gdcd.back.domain.post.PostRepository;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import com.gdcd.back.dto.image.response.ImageSimpleResponseDto;
import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.dto.post.response.PostDetailResponseDto;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public List<PostListResponseDto> findPosts(){
        List<Post> documentList = postRepository.findAll();
        List<PostListResponseDto> list = new ArrayList<>();
        for (Post post : documentList) {
            if (validPost(post)){
                ImageDetailResponseDto res = post.getImages().get(post.getRepresentative());
                list.add(new PostListResponseDto(post, res));
            }
        }
        return list;
    }
    public PostDetailResponseDto findPostById(Long postId){
        Post post = findPost(postId);
        if (validPost(post)){
            return new PostDetailResponseDto(post); // list(post)
        }else {
            return null;
        }
    }
    public PostCreateRequestDto addPost(String token, PostCreateRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        requestDto.setWriteNo(user.getId());
        requestDto.setWriterNickname(user.getNickname());
        requestDto.setWriterProfile(user.getProfile());
        List<ImageDetailResponseDto> images = new ArrayList<>();
        for (Long id : requestDto.getImages()){
            images.add(new ImageDetailResponseDto(findImage(id)));
        }
        requestDto.setImageList(images);
        postRepository.save(requestDto.toDocument());
        return requestDto;

    };
//    public PostCreateRequestDto addPost(PostCreateRequestDto requestDto){
////        String image = images.getOriginalFilename();
////        List<String> list = new ArrayList<>();
////        list.add(image);
////        requestDto.setImages(list);
//        return requestDto;
//
//    };
    public PostDetailResponseDto modifyPost(String token, PostUpdateRequestDto requestDto){
//        Post post = findPost(requestDto.getPostId());
//        if (validPost(post)){
//            post.setUpdateTime(LocalDateTime.now());
//            post.update(
//                    requestDto.getTitle(),
//                    requestDto.getContent(),
//                    requestDto.getPrivacyBound(),
//                    requestDto.getTag()
//            );
//            post.setId(requestDto.getPostId());
//            postRepository.save(post);
//
//            return new PostDetailResponseDto(post);
//        }else {
//            return null;
//        }
        return null;
    }

    public String removePost(Long postId){
        Post post = findPost(postId);
        if (validPost(post)){
            post.setValidation(false);
            postRepository.save(post);
            return "성공적으로 삭제되었습니다.";
        }else {
            return "존재하지 않는 게시글입니다";
        }
    }

    public PostReportRequestDto reportPost(PostReportRequestDto requestDto){
        return requestDto;
    }

    public Long likePost(Long postId){
        Post post = findPost(postId);
        // likes Document 만들어야 함 (user 구현 후)
        if (validPost(post)){
            //if (likes Document에 없다면)
            //post.setLikeCount(post.getLikeCount()+1);
            // like Document에 저장하기 (userId, postId)
            //else (like Document에 있다면)
            //post.setLikeCount(post.getLikeCount()-1);
            //like Document에서 삭제하기
            //postRepository.save(post);
            return postId;
        }else {
            return null;
        }
    }

    public Long scrapPost(Long postId){
        return postId;
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId + "은(는) 존재하지 않는 유저입니다."));
    }
    private Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번은(는) 존재하지 않는 게시글입니다."));
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

    public String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }

}
