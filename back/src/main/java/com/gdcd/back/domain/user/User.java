package com.gdcd.back.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Transient
    public static final String USER_SEQUENCE_NAME = "user_sequences";

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
