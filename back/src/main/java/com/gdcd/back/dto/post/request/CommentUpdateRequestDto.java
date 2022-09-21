package com.gdcd.back.dto.post.request;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long commentId;
    private String content;
    private LocalDateTime updateTime = LocalDateTime.now();
}
