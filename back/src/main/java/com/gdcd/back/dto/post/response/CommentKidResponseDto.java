package com.gdcd.back.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentKidResponseDto {
    private Long commentId = 2L;
    private String content = "your name is your name";
    private LocalDateTime updateTime = LocalDateTime.now();
}
