package com.gdcd.back.dto.image.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageOptProcessingRequestDto {
    private Long requestId;
    private int finished;
}
