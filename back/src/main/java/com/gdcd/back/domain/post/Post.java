package com.gdcd.back.domain.post;


import com.gdcd.back.domain.image.Image;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import lombok.*;
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
@Builder
@AllArgsConstructor
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
    private int representative;
    private List<ImageDetailResponseDto> images;

    @Field(name = "scrap_users")
    private List<Long> scrapUsers;

    @Field(name = "like_users")
    private List<Long> likeUsers;


    public void update(String title, String content, Long privacyBound, Integer representative) {
        if(title != null)
            this.title = title;
        if(content != null)
            this.content = content;
        if(privacyBound != null)
            this.privacyBound = privacyBound;
        if (representative != null)
            this.representative = representative;

//        if(images != null)
//            this.images = images;
    }
}
