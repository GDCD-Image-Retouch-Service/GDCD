package com.gdcd.back.service.image;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.dto.image.request.ImageCreateRequestDto;
import com.gdcd.back.dto.image.response.CoreScoreResponseDto;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import com.gdcd.back.dto.image.response.ImageListResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final String ROOT = "/app/data/images/";
    private final String ADDRESS = "https://j7b301.p.ssafy.io/api/image?imageId=";
    private final String CORE = "https://j7b301.p.ssafy.io/core/";
    private final String SCORE_IMAGE = "score-image";
    private Map<String, Object> RESULT_OBJECT;

    //    Local에서 진행할 폴더
//        String ROOT = "C:/test/images/";
//        String ADDRESS = "http://localhost:8081/api/image?imageId=";
    public Long addImage(String token, MultipartFile image, ImageCreateRequestDto requestDto) throws Exception {
//        ImageCreateRequestDto requestDto = new ImageCreateRequestDto();
        User user = findUserByEmail(decodeToken(token));
        Long urlCount = imageRepository.findAllByUserId(user.getId()).stream().count() + 1;
//        System.out.println(urlCount);
        String imageType = image.getContentType();
        String endPoint = "." + imageType.substring(imageType.lastIndexOf("/") + 1);
        String path = user.getId().toString();
        String urlName = "//" + urlCount.toString() + endPoint;
        String FilePath = path + urlName;
//        System.out.println(FilePath);
        File Folder = new File(ROOT + path);
        try {
            if (!Folder.exists()) {
                try {
                    Folder.mkdir();
//                    System.out.println("폴더가 생성되었습니다.");
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            image.transferTo(new File(ROOT + FilePath));
            requestDto.setFilePath(FilePath);
            Long count = imageRepository.findAll().stream().count() + 1;
            requestDto.setImgUrl(ADDRESS + count.toString());
            requestDto.setUserId(user.getId());
//            System.out.println(image.getContentType());
//            System.out.println(image.getResource());
            imageRepository.save(requestDto.toDocument());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlCount;
    }

    public byte[] findImageById(Long imageId) throws IOException {
        Image img = findImage(imageId);
        InputStream imageStream = new FileInputStream(ROOT + img.getFilePath());
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return imageByteArray;
    }

    public ImageDetailResponseDto findImageInfoById(Long imageId) {
        Image img = findImage(imageId);
        return new ImageDetailResponseDto(img);
    }

    public List<ImageListResponseDto> findImageList(String token) throws Exception {
        List<Image> imageList;
        User user = findUserByEmail(decodeToken(token));
        imageList = imageRepository.findAllByUserId(user.getId());

        List<ImageListResponseDto> images = new ArrayList<>();
        for (Image image : imageList) {
            images.add(new ImageListResponseDto(image));
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


    @Override
    public Map<String, Object> requestInitialScore(MultipartFile image) {
        RESULT_OBJECT = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
//            System.out.println("image type : " + image.getContentType());
//            System.out.println(MediaType.MULTIPART_FORM_DATA);
//            httpHeaders.setContentType(MediaType.parseMediaType(image.getContentType()));
//            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
            System.out.println(image.getInputStream());

//            MultiValueMap<String, MultipartFile> body = new LinkedMultiValueMap<>();
            MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
            //List<MultipartFile> image 의 경우
//            for (MultipartFile img : image){
//                body.add("images", img.getResource());
//            }

            body.add("images", image.getResource());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

//            System.out.println("여긴가?1");
            System.out.println(CORE+SCORE_IMAGE);

            HttpEntity<String> response = restTemplate.postForEntity(CORE+SCORE_IMAGE,requestMessage,String.class);

//            System.out.println("여긴가?2");

            ObjectMapper objectMapper = new ObjectMapper();

//            System.out.println("여긴가?3");
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

//            System.out.println("여긴가?4");
//            CoreScoreResponseDto responseDto = objectMapper.readValue(response.getBody(), CoreScoreResponseDto.class);
            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

//            System.out.println("여긴가?5");
//            RESULT_OBJECT.put("dict",responseDto);
            RESULT_OBJECT.put("dict",objects);
//            System.out.println("여긴가?6");
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "IMAGE NOT SCORED");
        }
        return RESULT_OBJECT;
    }


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
