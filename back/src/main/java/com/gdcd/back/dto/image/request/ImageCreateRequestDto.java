package com.gdcd.back.dto.image.request;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ImageCreateRequestDto {
    private Long userId ;
    private String imgUrl;
    private String filePath;
    private int aesthetic;
    private int quality;
//    private int rank;
    private LocalDateTime registDate;
    private List<String> objects = new ArrayList<>();

//    private Boolean before = true;



    public Image toDocument(){
        return Image.builder()
                .userId(userId)
                .imgUrl(imgUrl)
                .filePath(filePath)
                .aesthetic(aesthetic)
                .quality(quality)
                .registDate(LocalDateTime.now())
                .objects(objects)
//                .beforeImage(before)
                .build();
    }
}
