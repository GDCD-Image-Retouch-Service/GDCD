package com.gdcd.back.domain.image;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "images")
public class Image {
    @Transient
    public static final String IMAGE_SEQUENCE_NAME = "image_sequences";

    @Id
    @Field(name = "_id")
    private Long id;
    @Field(name = "user_id")
    private Long userId;
    @Field(name = "img_url")
    private String imgUrl;
    private int rank;
    @Field(name = "regist_date")
    private LocalDateTime registDate;
    private List<String> objects;

}
