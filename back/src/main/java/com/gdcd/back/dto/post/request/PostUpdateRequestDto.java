package com.gdcd.back.dto.post.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostUpdateRequestDto {
    private Long postId;
    private String title;
    private String content;
    private List<Long> images;
    private Long privacyBound;
    private int representative;
}
