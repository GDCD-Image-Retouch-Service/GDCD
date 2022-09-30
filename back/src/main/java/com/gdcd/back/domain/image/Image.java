package com.gdcd.back.domain.image;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)

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
    @Field(name="file_path")
    private String filePath;
//    private int rank;
    private int aesthetic;
    private int quality;
    @Field(name = "regist_date")
    private LocalDateTime registDate;
    private List<String> objects;

}
