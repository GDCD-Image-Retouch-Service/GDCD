package com.gdcd.back.dto.post.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PostUpdateRequestDto {
    private Long postId;
    private String title;
    private String content;
    private Long privacyBound;
    private List<String> image;
    private List<String> tag;
}
