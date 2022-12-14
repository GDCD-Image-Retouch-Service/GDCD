package com.gdcd.back.domain.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Transient
    public static final String USER_SEQUENCE_NAME = "user_sequences";

    @Id
    @Field(name = "_id")
    private Long id;
    @Indexed(unique = true)
    private String email;
    private String nickname;
    private String profile;
    @Field(name = "storage_path")
    private String storagePath;
    @Field(name = "regist_date")
    private LocalDateTime registDate;
    private Boolean validation;
    @Field(name = "post_count")
    private int postCount;
    @Field(name = "scrap_count")
    private int scrapCount;
    @Field(name = "scrap_posts")
    private List<Long> scrapPosts;

    @Field(name = "like_count")
    private int likeCount;
    @Field(name = "like_posts")
    private List<Long> likePosts;
    @Field(name = "follower_count")
    private int followerCount;
    @Field(name = "following_count")
    private int followingCount;
    @Field(name = "daily_reports")
    private int dailyReports;

    public void update(String requestURI, String profile, String nickname) {
        if (profile != null) {
            this.profile = requestURI + profile;
            this.storagePath = profile;
        }
        if (nickname != null)
            this.nickname = nickname;
    }

    public void updateProfile(String requestURI, String profile) {
        this.profile = requestURI + profile;
        this.storagePath = profile;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserSimple simplify() {
        return UserSimple.builder()
                .id(id)
                .nickname(nickname)
                .profile(profile)
                .validation(validation)
                .build();
    }

    public void delete(Boolean validation) {
        this.validation = validation;
    }

    public void addLikeCount() {
        this.likeCount++;
    }

    public void subLikeCount() {
        this.likeCount--;
    }

    public void addScrapCount() {
        this.scrapCount++;
    }

    public void subScrapCount() {
        this.scrapCount--;
    }

    public void addPostCount() {
        this.postCount++;
    }

    public void subPostCount() {
        this.postCount--;
    }

    public void addFollowerCount() {
        this.followerCount++;
    }

    public void subFollowerCount() {
        this.followerCount--;
    }

    public void addFollowingCount() {
        this.followingCount++;
    }

    public void subFollowingCount() {
        this.followingCount--;
    }
    public void addDailyReports(){this.dailyReports++;}

    public void cancelScrap(Long postId) {
        scrapPosts.remove(postId);
        subScrapCount();
    }

    public void cancelLike(Long postId) {
        likePosts.remove(postId);
        subLikeCount();
    }
}
