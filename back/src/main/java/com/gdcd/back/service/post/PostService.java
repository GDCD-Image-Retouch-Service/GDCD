package com.gdcd.back.service.post;

import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.dto.post.response.PostDetailResponseDto;
import com.gdcd.back.dto.post.response.PostListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public List<PostListResponseDto> findPosts();
    public PostDetailResponseDto findPostById(Long postId);
    public Long addPost(String token, PostCreateRequestDto requestDto) throws Exception;
    public Long modifyPost(String token, PostUpdateRequestDto requestDto);
    public String removePost(Long postId);

    public PostReportRequestDto reportPost(PostReportRequestDto requestDto);

    public Long likePost(Long postId);
    public Long scrapPost(Long postId);
}
