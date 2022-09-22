package com.gdcd.back.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public Integer addImage(MultipartFile image) throws IOException;
    public byte[] findImageById(Long imageId) throws IOException;

}
