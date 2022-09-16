package com.gdcd.back.domain.post;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "posts")
public class Post {
    @Id
    private Long _id;
    private Long writerNo;
    private String title;
    private String content;
    private Long privacyBound;
    private Date registDate;
    private Date updateDate;
    private Date deleteDate;
    private Boolean validation;
    private Integer reportCount;
    private String writerNickname;
    private String writerProfile;
    private Integer likeCount;
    private String representative;
}
