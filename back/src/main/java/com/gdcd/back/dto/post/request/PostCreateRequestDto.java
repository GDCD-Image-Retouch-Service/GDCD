package com.gdcd.back.dto.post.request;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Long writeNo;
    private String writerNickname;
    private String writerProfile;
    private Long privacyBound;
    private List<Long> images;

    private List<ImageDetailResponseDto> imageList;
    private int representative;
    private List<Long> scrapUsers = new ArrayList<>();
    private List<Long> likeUsers = new ArrayList<>();

    public Post toDocument(){
        return Post.builder()
                .writerNo(writeNo)
                .writerNickname(writerNickname)
                .writerProfile(writerProfile)
                .title(title)
                .content(content)
                .privacyBound(privacyBound)
                .registTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .images(imageList)
                .likeCount(0)
                .reportCount(0)
                .representative(representative)
                .validation(true)
                .likeUsers(likeUsers)
                .scrapUsers(scrapUsers)
                .build();
    }


}
