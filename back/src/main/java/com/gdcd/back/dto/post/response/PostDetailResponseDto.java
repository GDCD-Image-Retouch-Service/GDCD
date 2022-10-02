package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDetailResponseDto {
    private Long postId;
    private Long userId;
    private String writerNickname;
    private String writerProfile;
    private String title;
    private String content;
    private List<ImageDetailResponseDto> images;
    private int representative;
    private LocalDateTime registTime;
    private LocalDateTime updateTime;
    private Integer likeCount;
    private Long privacyBound;
    private Boolean scrap;
    private Boolean like;


    public PostDetailResponseDto(Post post, Boolean scrap, Boolean like) {
        this.postId = post.getId();
        this.userId = post.getWriterNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.privacyBound = post.getPrivacyBound();
        this.writerNickname = post.getWriterNickname();
        this.writerProfile = post.getWriterProfile();
        this.images = post.getImages();
        this.representative = post.getRepresentative();
        this.likeCount = post.getLikeCount();
        this.registTime = post.getRegistTime().plusHours(9);
        this.updateTime = post.getUpdateTime().plusHours(9);
        this.scrap = scrap;
        this.like = like;
    }
}
