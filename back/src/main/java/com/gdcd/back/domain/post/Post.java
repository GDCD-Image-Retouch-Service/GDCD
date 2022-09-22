package com.gdcd.back.domain.post;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "posts")
public class Post {
    @Id
    private String _id;
    private Long writerNo;
    private String title;
    private String content;
    private Long privacyBound;
    private LocalDateTime registTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
    private Boolean validation;
    private Integer reportCount;
    private String writerNickname;
    private String writerProfile;
    private Integer likeCount;
    private String representative;
    private List<String> tag;
    private List<String> images;


    public void update(String title, String content, Long privacyBound, List<String> tag) {
        if(title != null)
            this.title = title;
        if(content != null)
            this.content = content;
        if(privacyBound != null)
            this.privacyBound = privacyBound;
        if(tag != null)
            this.tag = tag;
//        if(images != null)
//            this.images = images;
    }
}
