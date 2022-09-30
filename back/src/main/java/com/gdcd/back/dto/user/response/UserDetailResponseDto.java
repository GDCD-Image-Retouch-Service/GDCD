package com.gdcd.back.dto.user.response;

import com.gdcd.back.domain.user.User;
import lombok.Getter;

@Getter
public class UserDetailResponseDto {
    private Long userId;
    private String profile;
    private String nickname;
    private int postCount;
    private int scrapCount;
    private int likeCount;
    private int followerCount;
    private int followingCount;
    private int dailyReports;


    public UserDetailResponseDto(User document){
        this.userId = document.getId();
        this.profile = document.getProfile();
        this.nickname = document.getNickname();
        this.postCount = document.getPostCount();
        this.scrapCount = document.getScrapCount();
        this.likeCount = document.getLikeCount();
        this.followerCount = document.getFollowerCount();
        this.followingCount = document.getFollowingCount();
        this.dailyReports = document.getDailyReports();
    }
}
