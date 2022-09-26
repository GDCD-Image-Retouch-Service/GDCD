package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.post.Post;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;

import java.time.LocalDateTime;

public class PostListByUserIdResponseDto {
    private Long postId;
    private Integer likeCount;

    private String title;
    private ImageDetailResponseDto images;
    private LocalDateTime updateTime;

    public PostListByUserIdResponseDto(Post post, ImageDetailResponseDto img) {
        this.postId =post.getId();
        this.title = post.getTitle();
        this.images = img;
        this.updateTime = post.getUpdateTime();
        this.likeCount = post.getLikeCount();
    }
}
