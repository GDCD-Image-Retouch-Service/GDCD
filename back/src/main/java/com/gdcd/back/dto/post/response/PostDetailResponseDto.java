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
    private LocalDateTime updateTime;
    private Integer likeCount;
    private Long privacyBound;
    private List<Long> scrapUsers;
    private List<Long> likeUsers;


    public PostDetailResponseDto(Post post) {
        this.postId = post.getId();
        this.userId = post.getWriterNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writerNickname = post.getWriterNickname();
        this.writerProfile = post.getWriterProfile();
        this.images = post.getImages();
        this.representative = post.getRepresentative();
        this.likeCount = post.getLikeCount();
        this.likeUsers = post.getLikeUsers();
        this.scrapUsers = post.getScrapUsers();
        this.updateTime = post.getUpdateTime();
    }
}
