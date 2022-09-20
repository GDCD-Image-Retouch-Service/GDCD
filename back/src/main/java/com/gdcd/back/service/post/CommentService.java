package com.gdcd.back.service.post;

import com.gdcd.back.dto.post.request.CommentCreateRequestDto;
import com.gdcd.back.dto.post.request.CommentUpdateRequestDto;
import com.gdcd.back.dto.post.response.CommentUpperResponseDto;

import java.util.List;

public interface CommentService {
    public List<CommentUpperResponseDto> findComments(Long postId);
    public CommentCreateRequestDto addComment(CommentCreateRequestDto requestDto);
    public CommentUpdateRequestDto modifyComment(CommentUpdateRequestDto requestDto);
    public Long deleteComment(Long commentId);
}
