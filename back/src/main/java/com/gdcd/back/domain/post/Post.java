package com.gdcd.back.domain.post;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "posts")
public class Post {

    @Transient
    public static final String POST_SEQUENCE_NAME = "post_sequences";

    @Id
    @Field(name = "_id")
    private Long id;
    @Field(name = "writer_no")
    private Long writerNo;
    private String title;
    private String content;
    @Field(name = "privacy_bound")
    private Long privacyBound;
    @Field(name = "regist_time")
    private LocalDateTime registTime;
    @Field(name = "update_time")
    private LocalDateTime updateTime;
    @Field(name = "delete_time")
    private LocalDateTime deleteTime;
    private Boolean validation;
    @Field(name = "report_count")
    private Integer reportCount;
    @Field(name = "writer_nickname")
    private String writerNickname;
    @Field(name = "writer_profile")
    private String writerProfile;
    @Field(name = "like_count")
    private Integer likeCount;
    private String representative;
    private List<String> tag;
    private List<String> images;



    @Builder
    public Post( Long writerNo,String title,String content,Long privacyBound,LocalDateTime updateTime,Boolean validation, Integer reportCount,String writerNickname,String writerProfile,Integer likeCount,String representative,List<String> tag,List<String> images){
        this.writerNo=writerNo;
        this.title = title;
        this.content = content;
        this.privacyBound = privacyBound;
        this.reportCount = reportCount;
        this.validation = validation;
        this.updateTime = updateTime;
        this.writerNickname = writerNickname;
        this.writerProfile = writerProfile;
        this.likeCount = likeCount;
        this.representative = representative;
        this.tag = tag;
        this.images = images;
    }

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
