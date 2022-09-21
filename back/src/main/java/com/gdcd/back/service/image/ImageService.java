package com.gdcd.back.service.image;

import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public Long addImage(String token, MultipartFile image) throws Exception;
    public byte[] findImageById(Long imageId) throws IOException;

//    public List<byte[]> findImageList(Long userId) throws IOException;
//    public List<String> findImageList(Long userId) throws IOException;
    public List<ImageDetailResponseDto> findImageList(String token) throws Exception;
//    public List<ImageDetailResponseDto> findImageList(Long userId) throws Exception;

}

