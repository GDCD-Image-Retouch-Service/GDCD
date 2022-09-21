package com.gdcd.back.service.image;

import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public Integer addImage(MultipartFile image) throws IOException;
    public byte[] findImageById(Long imageId) throws IOException;

//    public List<byte[]> findImageList(Long userId) throws IOException;
//    public List<String> findImageList(Long userId) throws IOException;
    public List<ImageDetailResponseDto> findImageList(Long userId) throws IOException;


}

