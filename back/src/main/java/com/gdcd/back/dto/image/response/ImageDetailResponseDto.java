package com.gdcd.back.dto.image.response;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class ImageDetailResponseDto {
    private String imageUrl;
    private Long imageId;


    public ImageDetailResponseDto(String imageUrl, Long imageId) {
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }
}
