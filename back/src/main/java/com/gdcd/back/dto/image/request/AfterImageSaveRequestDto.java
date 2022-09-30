package com.gdcd.back.dto.image.request;

import com.gdcd.back.domain.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AfterImageSaveRequestDto {
    private Long imageId; //원본아이디

    private String imageUrl; //요청된 url
    private int aesthetic;
    private int quality;

    public Image toDocument(Long userId, String imgUrl, String filePath, List<String> obj){
        return Image.builder()
                .userId(userId)
                .imgUrl(imgUrl)
                .filePath(filePath)
                .aesthetic(aesthetic)
                .quality(quality)
                .registDate(LocalDateTime.now())
                .objects(obj)
                .build();
    }
}
