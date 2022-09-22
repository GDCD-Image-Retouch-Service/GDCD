package com.gdcd.back.dto.image.response;

import com.gdcd.back.domain.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class ImageDetailResponseDto {
    private Long imageId;
    private List<String> imageTag;
    private int imageRank;


    public ImageDetailResponseDto( Image img) {
        this.imageId = img.getId();
        this.imageTag = img.getObjects();
        this.imageRank = img.getRank();
    }
}
