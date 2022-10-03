package com.gdcd.back.dto.image.response;

import com.gdcd.back.domain.image.Image;
import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageListResponseDto {
    ImageDetailResponseDto beforeImage;
    @Nullable
    ImageDetailResponseDto afterImage;

    public ImageListResponseDto(ImageDetailResponseDto img, ImageDetailResponseDto img2) {
        this.beforeImage = img;
        this.afterImage = img2;
    }
}
