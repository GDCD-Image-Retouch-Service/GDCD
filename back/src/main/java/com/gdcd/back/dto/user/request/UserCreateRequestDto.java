package com.gdcd.back.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdcd.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String email;
    private String nickname;
    private String profile;
    private String storagePath;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH;mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime registDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    private Boolean validation = true;
    private int postCount = 0;
    private int scrapCount = 0;
    private int followerCount = 0;
    private int followingCount = 0;
    private int dailyReports = 0;

//    private List<Long> scrapPosts = new ArrayList<>();
//    private List<Long> likePosts = new ArrayList<>();


    public User toDocument(String profileRequestURI, String storagePath) {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .profile(profileRequestURI + storagePath)
                .storagePath(storagePath)
                .registDate(registDate)
                .validation(validation)
                .postCount(postCount)
                .scrapCount(scrapCount)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .dailyReports(dailyReports)
                //추가
//                .scrapPosts(scrapPosts)
//                .likePosts(likePosts)
                .build();
    }
}
