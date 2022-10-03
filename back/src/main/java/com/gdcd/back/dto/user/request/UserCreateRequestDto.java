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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH;mm:ss")
    private LocalDateTime registDate = LocalDateTime.now();

    public User toDocument(String profileRequestURI, String storagePath) {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .profile(profileRequestURI + storagePath)
                .storagePath(storagePath)
                .registDate(registDate)
                .validation(true)
                .postCount(0)
                .scrapCount(0)
                .scrapPosts(new ArrayList<>())
                .likeCount(0)
                .likePosts(new ArrayList<>())
                .followerCount(0)
                .followingCount(0)
                .dailyReports(0)
                .build();
    }
}
