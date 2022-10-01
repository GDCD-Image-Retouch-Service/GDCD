package com.gdcd.back.service.image;

import com.gdcd.back.dto.image.request.AfterImageSaveRequestDto;
import com.gdcd.back.dto.image.request.ImageCreateRequestDto;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import com.gdcd.back.dto.image.response.ImageListResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ImageService {
    public Long addImage(String token, MultipartFile image, ImageCreateRequestDto requestDto) throws Exception;
    public byte[] findImageById(Long imageId, String from) throws IOException;
    public ImageDetailResponseDto findImageInfoById(Long imaageId);
    public Map<LocalDate, List<ImageListResponseDto>> findImageList(String token) throws Exception;
//    public List<ImageDetailResponseDto> findImageList(Long userId) throws Exception;
//    public Map<String, Object> requestInitialScore(List<MultipartFile> image);
    public Map<String, Object> requestInitialScore(MultipartFile image);

    public List<String> requestObjectDetection(Long imageId);
//    public List<Object> requestObjectDetection(MultipartFile image);

    public Map<String, Object> requestOptimization(String token, MultipartFile image);

    public Long addAfterImage(String token, AfterImageSaveRequestDto requestDto) throws Exception;


}

