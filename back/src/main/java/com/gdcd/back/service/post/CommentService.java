package com.gdcd.back.service.post;

import com.gdcd.back.dto.post.request.CommentCreateRequestDto;
import com.gdcd.back.dto.post.request.CommentUpdateRequestDto;
import com.gdcd.back.dto.post.response.CommentUpperResponseDto;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public Map<String, Object> findComments(String token, Long postId);
    public Map<String, Object> addComment(String token, CommentCreateRequestDto requestDto);
    public Map<String, Object> modifyComment(String token, CommentUpdateRequestDto requestDto);
    public Map<String, Object> deleteComment(String token, Long commentId);
}
