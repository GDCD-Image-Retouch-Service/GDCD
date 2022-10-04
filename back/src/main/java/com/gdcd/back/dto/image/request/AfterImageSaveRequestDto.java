package com.gdcd.back.dto.image.request;

import com.gdcd.back.domain.image.Image;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AfterImageSaveRequestDto {
    private Long imageId;

    private String imageUrl;
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
                .beforeImage(false)
                .build();
    }
}
