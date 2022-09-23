package com.gdcd.back.dto.image.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoreScoreResponseDto {
    private float aesthetic;
    private float quality;
}
