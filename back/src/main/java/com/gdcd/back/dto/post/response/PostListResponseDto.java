package com.gdcd.back.dto.post.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Integer userId = 12;
    private String title = "전체 리스트 조회 제목";
    private String Image = "전체 리스트 사진";
    private Integer rank = 3;
    private LocalDateTime updateTime = LocalDateTime.now();
}
