package com.gdcd.back.dto.image.request;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ImageCreateRequestDto {
    private Long userId;
    private String imgUrl;
    private int rank;
    private LocalDateTime registDate;
    private List<String> objects;



    public Image toDocument(){
        return Image.builder()
                .userId(userId)
                .imgUrl(imgUrl)
                .rank(rank)
                .registDate(registDate)
                .objects(objects)
                .build();
    }
}
