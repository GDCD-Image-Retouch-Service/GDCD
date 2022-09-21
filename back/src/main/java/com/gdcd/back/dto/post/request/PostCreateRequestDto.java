package com.gdcd.back.dto.post.request;

import com.gdcd.back.domain.post.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
//@Data
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Long privacyBound;
    private List<String> tag;
    private List<String> images;
    private LocalDateTime registTime = LocalDateTime.now();
    private LocalDateTime updateTime;
    private Boolean validation = true;
    private Integer reportCount = 0;
    private String writerNickname;
    private String writerProfile;
    private Integer likeCount = 0;
    private String  representative;

    public Post toDocument(){
        return Post.builder()
                .title(title)
                .content(content)
                .privacyBound(privacyBound)
                .tag(tag)
                .updateTime(updateTime)
                .images(images)
                .likeCount(likeCount)
                .reportCount(reportCount)
                .representative(representative)
                .validation(validation)
                .build();
    }


}
