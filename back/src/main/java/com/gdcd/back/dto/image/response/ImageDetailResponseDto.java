package com.gdcd.back.dto.image.response;

import com.gdcd.back.domain.image.Image;
import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ImageDetailResponseDto {
    private Long imageId;
    private List<String> imageTag;
    private String imageUrl;
    private int imageAesthetic;
    private int imageQuality;


    public ImageDetailResponseDto(Image img) {
        this.imageId = img.getId();
        this.imageTag = img.getObjects();
        this.imageAesthetic = img.getAesthetic();
        this.imageQuality = img.getQuality();
        this.imageUrl = img.getImgUrl();
    }
}
