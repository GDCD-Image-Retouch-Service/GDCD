package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostListResponseDto {
    private Integer userId;
    private String writerNickname;
    private String writerProfile;
    private Integer likeCount;

    private String title;
    private String representative;
    private List<Integer> rank;
    private LocalDateTime updateTime;

    public PostListResponseDto(Post post) {
//        this.userId =post.getUserId();
//        this.writerNickname = post.getWriterNickname();
//        this.writerProfile = post.getWriterProfile();
        this.title = post.getTitle();
        this.representative = post.getRepresentative();
        this.updateTime = post.getUpdateTime();
        this.likeCount = post.getLikeCount();
    }
}
