package com.gdcd.back.domain.user;

import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
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

    public void update(String profile, String nickname) {
        if (profile != null)
            this.profile = profile;
        if (nickname != null)
            this.nickname = nickname;
    }

    public void delete(Boolean validation){
        this.validation = validation;
    }

    public void addLikeCount(){
        this.likeCount++;
    }
    public void subLikeCount(){
        this.likeCount--;
    }
    public void addScrapCount(){
        this.scrapCount++;
    }
    public void subScrapCount(){
        this.scrapCount--;
    }
    public void addPostCount(){
        this.postCount++;
    }
    public void subPostCount(){
        this.postCount--;
    }
    public void addFollowerCount(){
        this.followerCount++;
    }
    public void subFollowerCount(){
        this.followerCount--;
    }
    public void addFollowingCount(){
        this.likeCount++;
    }
    public void subFollowingCount(){
        this.likeCount--;
    }

}
