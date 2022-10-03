package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.post.Post;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListByUserIdResponseDto {
    private Long postId;
    private Integer likeCount;
    private String title;
    private ImageDetailResponseDto images;
    private LocalDateTime registTime;
    private LocalDateTime updateTime;

    public PostListByUserIdResponseDto(Post post, ImageDetailResponseDto img) {
        this.postId =post.getId();
        this.title = post.getTitle();
        this.images = img;
        this.registTime = post.getRegistTime().plusHours(9);
        this.updateTime = post.getUpdateTime().plusHours(9);
        this.likeCount = post.getLikeCount();
    }
}
