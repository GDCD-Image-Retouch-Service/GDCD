package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import com.gdcd.back.dto.image.response.ImageSimpleResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDetailResponseDto {
    private Long postId;
    private String writerNickname;
    private String writerProfile;
//    private List<String> images;

    private Integer rank;
    private String title;
    private String content;
    private List<String> tag;
    private LocalDateTime updateTime;
    private Integer likeCount;
    private Long privacyBound;
//    private List<ImageSimpleResponseDto> images;


    public PostDetailResponseDto(Post post) {
//        this.images = list;
        this.postId = post.get_id();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.tag = post.getTag();
        this.updateTime = post.getUpdateTime();
    }
}
