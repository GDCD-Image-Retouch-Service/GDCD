package com.gdcd.back.dto.image.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageSimpleResponseDto {
    private String imageUrl;
    private Integer rank = 1;
}
