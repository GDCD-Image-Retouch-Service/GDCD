package com.gdcd.back.dto.image.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InpaintingRequestDto {
    private Long imageId;
    private List<Float[]> objects;
}
