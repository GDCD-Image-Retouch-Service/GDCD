package com.gdcd.back.service.image;

import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.dto.image.request.ImageCreateRequestDto;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

        String ROOT = "./data/images/";
        String ADDRESS = "https://j7b301.p.ssafy.io/api/image?imageId=";

    //    Local에서 진행할 폴더
    //    String ROOT = "C:\\test\\images\\";
    //    String ADDRESS = "http://localhost:8081/api/image?imageId=";
    public Long addImage(String token, MultipartFile image) throws Exception {
        ImageCreateRequestDto requestDto = new ImageCreateRequestDto();
        User user = findUserByEmail(decodeToken(token));
        Long urlCount = imageRepository.findAllByUserId(user.getId()).stream().count()+1;
        String imageType = image.getContentType();
        String endPoint = "."+imageType.substring(imageType.lastIndexOf("/")+1);
        String path = user.getId().toString();
        String urlName = "//"+urlCount.toString()+endPoint;
        String FilePath = path + urlName;
        System.out.println(FilePath);
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
            requestDto.setImgUrl(FilePath);
            requestDto.setRegistDate(LocalDateTime.now());
            requestDto.setUserId(user.getId());
            imageRepository.save(requestDto.toDocument());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlCount;
    }

    public byte[] findImageById(Long imageId) throws IOException{
        Image img = findImage(imageId);
        InputStream imageStream = new FileInputStream(ROOT + img.getImgUrl());
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return imageByteArray;
    }


    public List<ImageDetailResponseDto> findImageList(String token) throws Exception{
        List<Image> imageList;
        User user = findUserByEmail(decodeToken(token));
        imageList = imageRepository.findAllByUserId(user.getId());

        List<ImageDetailResponseDto> images = new ArrayList<>();
        for (Image image : imageList){
            String url = ADDRESS + image.getId().toString();
            images.add(new ImageDetailResponseDto(url, image.getId()));
        }
        return images;
    }
//    public List<ImageDetailResponseDto> findImageList(Long userId) throws Exception{
//        List<Image> imageList;
//        imageList = imageRepository.findAllByUserId(userId);
//
//        List<ImageDetailResponseDto> images = new ArrayList<>();
//        for (Image image : imageList){
//            String url = ADDRESS + image.getId().toString();
//            images.add(new ImageDetailResponseDto(url, image.getId()));
//        }
//        return images;
//    }

    private Image findImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException(imageId + "번은(는) 존재하지 않는 게시글입니다."));
    }

    private User findUserByEmail(String email) throws Exception {
        if (userRepository.findByEmail(email).isPresent())
            return userRepository.findByEmail(email).get();
        else
            throw new Exception("User Not Found");
    }

    public String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }
}
