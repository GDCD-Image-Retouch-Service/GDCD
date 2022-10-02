package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.post.Post;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostListResponseDto {
    private Long postId;
    private String writerNickname;
    private String writerProfile;
    private Integer likeCount;

    private String title;

    private Boolean scrap;
    private Boolean like;
    private ImageDetailResponseDto images;
    private LocalDateTime updateTime;

    public PostListResponseDto(Post post, ImageDetailResponseDto img, Boolean scrap, Boolean like) {
        this.postId =post.getId();
        this.writerNickname = post.getWriterNickname();
        this.writerProfile = post.getWriterProfile();
        this.title = post.getTitle();
        this.images = img;
//        this.representative = post.getRepresentative();
        this.updateTime = post.getUpdateTime().plusHours(9);
        this.likeCount = post.getLikeCount();
        this.scrap = scrap;
        this.like = like;
    }
}
