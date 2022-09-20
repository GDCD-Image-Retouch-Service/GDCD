package com.gdcd.back.service.image;

import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.dto.image.request.ImageCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    String ROOT = "C:\\test\\images\\";
    String path = "Test\\";

    Integer number = 1;
    String JPG = ".jpg";

    public Integer addImage(MultipartFile image) throws IOException {
        ImageCreateRequestDto requestDto = new ImageCreateRequestDto();
        String urlName = number.toString() + JPG;
        String FilePath = path + urlName;
        File Folder = new File(ROOT + path);
        try {
            if (!Folder.exists()) {
                try {
                    Folder.mkdir();
                    System.out.println("폴더가 생성되었습니다.");
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            image.transferTo(new File(ROOT + FilePath));
            number += 1;
            requestDto.setImgUrl(FilePath);
            requestDto.setRegistDate(LocalDateTime.now());
            imageRepository.save(requestDto.toDocument());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number-1;
    }

    public byte[] findImageById(Long imageId) throws IOException{
        Image img = findImage(imageId);
        InputStream imageStream = new FileInputStream(ROOT + img.getImgUrl());
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return imageByteArray;
    }


    private Image findImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException(imageId + "번은(는) 존재하지 않는 게시글입니다."));
    }
}
