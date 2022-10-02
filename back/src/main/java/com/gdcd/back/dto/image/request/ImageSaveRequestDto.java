package com.gdcd.back.dto.image.request;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.data.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageSaveRequestDto {
    private Long imageId;
    private int aesthetic;
    private int quality;



    public Data toDocument(){
        return Data.builder()
                .imageId(imageId)
                .aesthetic(9-aesthetic)
                .quality(9-quality)
                .build();
    }
}
