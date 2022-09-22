package com.gdcd.back.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private Long _id;
    private String email;
    private String nickname;
    private String profile;
    private Date registDate;
    private Boolean validation;
    private Long postCount;
    private Long scrapCount;
    private Long followerCount;
    private Long followingCount;
    private Long dailyReports;

    public void update(String profile, String nickname) {
        if (profile != null)
            this.profile = profile;
        if (nickname != null)
            this.nickname = nickname;
    }

    public void delete(Boolean validation){
        this.validation = validation;
    }
}
