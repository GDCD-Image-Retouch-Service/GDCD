package com.gdcd.back.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpperResponseDto {
    private Long commentId = 1L;
    private String content = "my name is my name";
    private LocalDateTime updateTime = LocalDateTime.now();
    private List<CommentKidResponseDto> kids;
}
