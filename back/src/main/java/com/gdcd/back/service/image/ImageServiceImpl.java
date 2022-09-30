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
//import jdk.internal.util.xml.impl.Input;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private final String DETECT_OBJECT = "detect-object";
    private final String OPTIMIZE_IMAGE = "optimize-image";
    private Map<String, Object> RESULT_OBJECT;

    private final String BEFORE = "/before";
    private final String AFTER = "/after";

    //    Local에서 진행할 폴더
//        String ROOT = "C:/test/images/";
//        String ADDRESS = "http://localhost:8081/api/image?imageId=";
    public Long addImage(String token, MultipartFile image, ImageCreateRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        Long urlCount = imageRepository.findAllByUserId(user.getId()).stream().count() + 1;
        String imageType = image.getContentType();
//        System.out.println(imageType);
        String endPoint = "." + imageType.substring(imageType.lastIndexOf("/") + 1);
        String path = user.getId().toString();
        String urlName = "/" + urlCount.toString() + endPoint;
        String FilePath = path+BEFORE+ urlName;
        File Folder = new File(ROOT + path);
        try {
            if (!Folder.exists()) {
                try {
                    Folder.mkdir();
                    File Folder2 = new File(ROOT+path+BEFORE);
                    if (!Folder2.exists()){
                        Folder2.mkdir();
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            image.transferTo(new File(ROOT + FilePath));
            requestDto.setObjects(new ArrayList<>());
            requestDto.setFilePath(FilePath);
            Long count = imageRepository.findAll().stream().count() + 1;
            requestDto.setImgUrl(ADDRESS + count.toString());
            requestDto.setUserId(user.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageRepository.save(requestDto.toDocument()).getId();
    }

    public byte[] findImageById(Long imageId, String from) throws IOException {
        if (from == null) {
            Image img = findImage(imageId);
            InputStream imageStream = new FileInputStream(ROOT + img.getFilePath());
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            return imageByteArray;
        } else {
            InputStream imageStream = new FileInputStream(from);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            return imageByteArray;
        }
    }

    public ImageDetailResponseDto findImageInfoById(Long imageId) {
        Image img = findImage(imageId);
        return new ImageDetailResponseDto(img);
    }

    public Map<LocalDate, List<ImageListResponseDto>> findImageList(String token) throws Exception {
        List<Image> imageList;
        User user = findUserByEmail(decodeToken(token));
        imageList = imageRepository.findAllByUserId(user.getId());


        //SET으로 중복 없애기
        Set<LocalDate> dateTime = new HashSet<>();
        for (Image img : imageList) {
            dateTime.add(img.getRegistDate().toLocalDate());
        }
        List<LocalDate> dateTimeList = new ArrayList<>(dateTime);
        dateTimeList.sort(Comparator.reverseOrder());

        Map<LocalDate, List<ImageListResponseDto>> images = new HashMap<>();
        for (LocalDate datetime : dateTimeList) {
            List<ImageListResponseDto> list = new ArrayList<>();
            // 원래 코드 1
//            for (Image image : imageList){
//                if (image.getRegistDate().toLocalDate().equals(datetime)){
//                    list.add(new ImageListResponseDto(new ImageDetailResponseDto(image), new ImageDetailResponseDto(afterImage)));
            for (int i = 0; i < imageList.size() / 2; i++) {
                if (imageList.get(2 * i).getRegistDate().toLocalDate().equals(datetime)) {
                    list.add(new ImageListResponseDto(new ImageDetailResponseDto(imageList.get(2*i)), new ImageDetailResponseDto(imageList.get(2*i+1))));
                }
            }
            images.put(datetime, list);
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
            System.out.println(CORE + SCORE_IMAGE);

            HttpEntity<String> response = restTemplate.postForEntity(CORE + SCORE_IMAGE, requestMessage, String.class);

//            System.out.println("여긴가?2");

            ObjectMapper objectMapper = new ObjectMapper();

//            System.out.println("여긴가?3");
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

//            System.out.println("여긴가?4");
//            CoreScoreResponseDto responseDto = objectMapper.readValue(response.getBody(), CoreScoreResponseDto.class);
            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

//            System.out.println("여긴가?5");
//            RESULT_OBJECT.put("dict",responseDto);
            RESULT_OBJECT.put("dict", objects);
//            System.out.println("여긴가?6");
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "IMAGE NOT SCORED");
        }
        return RESULT_OBJECT;
    }

//    public Map<String, Object> requestObjectDetection(MultipartFile image, Long imageId) {
    public List<String> requestObjectDetection(MultipartFile image, Long imageId) {

//    RESULT_OBJECT = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
            System.out.println(image.getInputStream());

            MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();


            body.add("image", image.getResource());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);


            HttpEntity<String> response = restTemplate.postForEntity(CORE + DETECT_OBJECT, requestMessage, String.class);


            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

//            RESULT_OBJECT.put("dict", objects);
//            List<Object> objectList = new ArrayList<>();
//            System.out.println(RESULT_OBJECT.keySet());
//            for (Object obj : objects) {
//                objectList.add(obj);
//            }
            Set<String> tags = new HashSet<>();
            for (Object obj : objects) {
                Map<String, Object> objs = objectMapper.convertValue(obj, Map.class);
                tags.add(objs.get("class").toString());
            }
            List<String> objectTag = new ArrayList<>(tags);
            Image img = findImage(imageId);
            img.setObjects(objectTag);
            imageRepository.save(img);


            List<String> objectList = new ArrayList<>();
            for (String str : tags){
                String string = new String(str+";");
                for (Object object : objects){
                    Map<String, Object> obj2 = objectMapper.convertValue(object, Map.class);
                    if (str.equals(obj2.get("class"))){
                        ArrayList<String> ul = (ArrayList<String>) obj2.get("ul");
                        ArrayList<String> dr = (ArrayList<String>) obj2.get("dr");
                        string += String.valueOf(ul.get(0))+","+String.valueOf(ul.get(1))+";"+String.valueOf(dr.get(0))+","+String.valueOf(dr.get(1))+";";
                    }
                }
                objectList.add(string);
            }
            return objectList;
        } catch (Exception e) {
            e.printStackTrace();
            List<String> errMessage = new ArrayList<>();
            errMessage.add("IMAGE NOT SCORED");
            return errMessage;
        }
    }

//    public List<Object> requestObjectDetection(MultipartFile image) {
//        RESULT_OBJECT = new HashMap<>();
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
//            System.out.println(image.getInputStream());
//
//            MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
//
//
//            body.add("image", image.getResource());
//
//            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
//
//
//            HttpEntity<String> response = restTemplate.postForEntity(CORE+DETECT_OBJECT,requestMessage,String.class);
//
//
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//
//            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);
//
//            RESULT_OBJECT.put("dict",objects);
//            List<Object> objectList = new ArrayList<>();
//            System.out.println(RESULT_OBJECT.keySet());
//            for (Object obj : objects){
//                Map<String, Object> object = objectMapper.convertValue(obj, Map.class);
//                Map<String, Object> res = new HashMap<String, Object>();
//                for (String str : object.keySet()){
//
//                }
//                // obj를 map으로 convert
//                // map<String, Object>
//                // map = new Map(String, Object)
//                // map.keySet -> for (String str : map.KeySet) //
//                // str =
//                // map.put(newkey, map.get(str))
//                objectList.add(obj);
//                System.out.println(obj.toString());
//            }
//            return objectList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            RESULT_OBJECT.put("error", "IMAGE NOT SCORED");
//        }
//        return null;
//    }

    public Map<String, Object> requestOptimization(String token, MultipartFile image) {
        RESULT_OBJECT = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
            System.out.println(image.getInputStream());

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();


            body.add("image", image.getResource());
            body.add("user_id", findUserByEmail(decodeToken(token)).getId());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);


            HttpEntity<String> response = restTemplate.postForEntity(CORE + OPTIMIZE_IMAGE, requestMessage, String.class);


            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

            RESULT_OBJECT.put("dict", objects);

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
