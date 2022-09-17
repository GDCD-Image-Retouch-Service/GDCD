package com.gdcd.back.dto.post.request;

import com.gdcd.back.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Long privacyBound;
    private List<String> tag;
    private List<String> image;
    private LocalDateTime updateTime = LocalDateTime.now();

}
