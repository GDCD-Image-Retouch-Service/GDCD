package com.gdcd.back.domain.image;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "images")
public class Image {
    @Id
    private String _id;
    private String userId;
    private String imgUrl;
    private int rank;
    private LocalDateTime registDate;
    private List<String> objects;

}
