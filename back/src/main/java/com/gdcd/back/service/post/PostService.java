package com.gdcd.back.service.post;

import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.dto.post.response.PostDetailResponseDto;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import com.gdcd.back.dto.post.response.PostListByUserIdResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    public List<PostListResponseDto> findPosts(String token, Pageable pageable) throws Exception;
    public List<PostListResponseDto> findPostsByUser(String token, Long userId, Pageable pageable) throws Exception;
    public PostDetailResponseDto findPostById(String token, Long postId) throws Exception;
    public Long addPost(String token, PostCreateRequestDto requestDto) throws Exception;
    public Long modifyPost(String token, PostUpdateRequestDto requestDto);
    public String removePost(String token, Long postId) throws Exception;

    public Long reportPost(String token, PostReportRequestDto requestDto) throws Exception;
    public Long likePost(String token, Long postId) throws Exception;

    public Long scrapPost(String token, Long postId) throws Exception;
}
