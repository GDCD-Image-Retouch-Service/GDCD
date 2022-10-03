package com.gdcd.back.dto.image.request;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ImageCreateRequestDto {
    private Long userId ;
    private String imgUrl;
    private String filePath;
    private int aesthetic;
    private int quality;
    private List<String> objects = new ArrayList<>();

    public Image toDocument(){
        return Image.builder()
                .userId(userId)
                .imgUrl(imgUrl)
                .filePath(filePath)
                .aesthetic(aesthetic)
                .quality(quality)
                .registDate(LocalDateTime.now())
                .objects(objects)
                .beforeImage(true)
                .build();
    }
}
