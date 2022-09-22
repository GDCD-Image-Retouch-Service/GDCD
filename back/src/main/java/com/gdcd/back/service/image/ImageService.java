package com.gdcd.back.service.image;

import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import com.gdcd.back.dto.image.response.ImageListResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public Long addImage(String token, MultipartFile image) throws Exception;
    public byte[] findImageById(Long imageId) throws IOException;
    public ImageDetailResponseDto findImageInfoById(Long imaageId);
    public List<ImageListResponseDto> findImageList(String token) throws Exception;
//    public List<ImageDetailResponseDto> findImageList(Long userId) throws Exception;

}

