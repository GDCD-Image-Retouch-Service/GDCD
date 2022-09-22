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
    private String writerNickname;
    private String writerProfile;
    private Long privacyBound;
    private List<String> images;
    private int representative;
    private Long writeNo;

    public Post toDocument(){
        return Post.builder()
                .writerNo(writeNo)
                .title(title)
                .content(content)
                .privacyBound(privacyBound)
                .registTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .images(images)
                .likeCount(0)
                .reportCount(0)
                .representative(representative)
                .validation(true)
                .build();
    }


}
