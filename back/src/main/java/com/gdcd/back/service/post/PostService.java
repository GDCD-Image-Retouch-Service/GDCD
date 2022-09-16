package com.gdcd.back.service.post;

import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.dto.post.response.PostDetailResponseDto;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    public List<PostListResponseDto> findPosts();
    public PostDetailResponseDto findPostById(Long postId);
    public PostCreateRequestDto addPost(PostCreateRequestDto requestDto);
    public PostUpdateRequestDto modifyPost(PostUpdateRequestDto requestDto);
    public Long removePost(Long postId);

    public PostReportRequestDto reportPost(PostReportRequestDto requestDto);

    public Long likePost(Long postId);
    public Long scrapPost(Long postId);
}
