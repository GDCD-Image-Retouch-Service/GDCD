package com.gdcd.back.dto.image.response;

import com.gdcd.back.domain.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageListResponseDto {
    private String imageUrl;
    private Long imageId;
    private List<String> imageTag;
    private int imageRank;


    public ImageListResponseDto(String imageUrl, Image img) {
        this.imageUrl = imageUrl;
        this.imageId = img.getId();
        this.imageTag = img.getObjects();
        this.imageRank = img.getRank();
    }
}
