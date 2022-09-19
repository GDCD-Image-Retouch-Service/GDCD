package com.gdcd.back.dto.post.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostUpdateRequestDto {
    private String postId;
    private String title;
    private String content;
    private Long privacyBound;
//    private List<String> images;
    private List<String> tag;
}
