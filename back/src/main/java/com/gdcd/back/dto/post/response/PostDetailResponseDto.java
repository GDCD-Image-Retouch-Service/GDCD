package com.gdcd.back.dto.post.response;

import com.gdcd.back.domain.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDetailResponseDto {
    private List<String> images;
    private Integer rank = 2;
    private String title = "title 입니다.";
    private String content = "이건 content 입니다. 오늘 저녁 뭐먹지?";
    private List<String> objects;
    private LocalDateTime updateTime = LocalDateTime.now();
}
