package com.gdcd.back.service.post;

import com.gdcd.back.domain.comment.CommentRepository;
import com.gdcd.back.dto.post.request.CommentCreateRequestDto;
import com.gdcd.back.dto.post.request.CommentUpdateRequestDto;
import com.gdcd.back.dto.post.response.CommentKidResponseDto;
import com.gdcd.back.dto.post.response.CommentUpperResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public List<CommentUpperResponseDto> findComments(Long postId) {
        List<CommentUpperResponseDto> list = new ArrayList<>();
        List<CommentKidResponseDto> kidList = new ArrayList<>();

        kidList.add(new CommentKidResponseDto());
        list.add(CommentUpperResponseDto.builder()
                .commentId(1L)
                .content("my name is my name")
                .updateTime(LocalDateTime.now())
                .kids(kidList)
                .build());

        return list;
    }

    @Override
    public CommentCreateRequestDto addComment(CommentCreateRequestDto requestDto) {
        return requestDto;
    }

    @Override
    public CommentUpdateRequestDto modifyComment(CommentUpdateRequestDto requestDto) {
        return requestDto;
    }

    @Override
    public Long deleteComment(Long commentId) {
        return commentId;
    }
}
