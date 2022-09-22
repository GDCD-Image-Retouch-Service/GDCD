package com.gdcd.back.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdcd.back.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String email;
    private String nickname;
    private String profile = "DEFAULT IMAGE URL";
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH;mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime registDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    private Boolean validation = true;
    private int postCount = 0;
    private int scrapCount = 0;
    private int followerCount = 0;
    private int followingCount = 0;
    private int dailyReports = 0;


    public User toDocument() {
        return User.builder()
                .email(email)
                .nickname(nickname) // default nickname creator
                .profile(profile) // default profile url
                .registDate(registDate)
                .validation(validation)
                .postCount(postCount)
                .scrapCount(scrapCount)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .dailyReports(dailyReports)
                .build();
    }
}
