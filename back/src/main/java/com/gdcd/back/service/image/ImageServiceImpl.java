package com.gdcd.back.service.image;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.domain.image.data.DataRepository;
import com.gdcd.back.domain.image.optrequest.OptRequest;
import com.gdcd.back.domain.image.optrequest.OptRequestRepository;
import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.dto.image.request.*;
import com.gdcd.back.dto.image.response.ImageDetailResponseDto;
import com.gdcd.back.dto.image.response.ImageListResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OptRequestRepository optRequestRepository;
    private final DataRepository dataRepository;
//    private final String ROOT = "/app/data/images/";
//    private final String ADDRESS = "https://j7b301.p.ssafy.io/api/image?imageId=";
    private final String CORE = "https://j7b301.p.ssafy.io/core/";
    private final String SCORE_IMAGE = "score-image";
    private final String DETECT_OBJECT = "detect-object";
    private final String OPTIMIZE_IMAGE = "optimize-image";
    private final String OPTIMIZE_REQUEST = "optimize-request";
    private final String SCORING_PATHS = "score-image-by-user-id";
    private final String INPAINT_IMAGE = "inpaint-image";
    private Map<String, Object> RESULT_OBJECT;

    private final String BEFORE = "/before";
    private final String AFTER = "/after";
//    private final String BUFFER = "/app/data/buffer/";

    //    Local에서 진행할 폴더
        String ROOT = "C:/test/images/";
        String BUFFER = "C:/test/buffer/";
        String ADDRESS = "http://localhost:8081/api/image?imageId=";
    public Long addImage(String token, MultipartFile image, ImageCreateRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        Long urlCount = imageRepository.findAllByUserId(user.getId()).stream().count() + 1;
        String imageType = image.getContentType();
//        System.out.println(imageType);
        String endPoint = "." + imageType.substring(imageType.lastIndexOf("/") + 1);
        String path = user.getId().toString();
        String urlName = "/" + urlCount.toString() + endPoint;
        String FilePath = path + BEFORE + urlName;
        File Folder = new File(ROOT + path);
        try {
            if (!Folder.exists()) {
                try {
                    Folder.mkdir();
                    File Folder2 = new File(ROOT + path + BEFORE);
                    if (!Folder2.exists()) {
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

//    public List<LocalDate> findImageList(String token) throws Exception {
    public Map<LocalDate, List<ImageListResponseDto>> findImageList(String token, Pageable pageable) throws Exception {
//        List<Image> imageList;
        User user = findUserByEmail(decodeToken(token));
        List<Image> imageList = imageRepository.findAllByUserIdAndBeforeImage(user.getId(), true);


        //SET으로 중복 없애기
        Set<LocalDate> dateTime = new HashSet<>();
        for (Image img : imageList) {
            dateTime.add(img.getRegistDate().toLocalDate());
        }
        List<LocalDate> dateTimeList = new ArrayList<>(dateTime);
        dateTimeList.sort(Comparator.reverseOrder());

        Map<LocalDate, List<ImageListResponseDto>> images = new LinkedHashMap<>();
        for (LocalDate datetime : dateTimeList) {
            List<ImageListResponseDto> list = new ArrayList<>();
            // 원래 코드 1
            for (Image image : imageList) {
                if (image.getRegistDate().toLocalDate().equals(datetime)) {
                    System.out.println(datetime);
                    Optional<Image> afterImage = imageRepository.findByFilePath(image.getFilePath().replace("before","after"));
                    if (afterImage.isPresent()){
                        list.add(new ImageListResponseDto(new ImageDetailResponseDto(image), new ImageDetailResponseDto(afterImage.get())));
                    }
                    else {
                        list.add(new ImageListResponseDto(new ImageDetailResponseDto(image), null));
                    }
                }
            }
            images.put(datetime, list);
            System.out.println(datetime);
        }
        return images;
//        return dateTimeList;
    }
//    public List<ImageDetailResponseDto> findImageList(String token) throws Exception{
//        List<Image> imageList;
//        User user = findUserByEmail(decodeToken(token));
//        imageList = imageRepository.findAllByUserIdAndBeforeImage(user.getId(),true);
////        imageList = imageRepository.findAllByUserId(user.getId());
////        imageList = imageRepository.findAllByDone(true);
//        List<ImageDetailResponseDto> images = new ArrayList<>();
//        for (Image image : imageList){
//            images.add(new ImageDetailResponseDto(image));
//        }
//        return images;
//    }

//                if (image.getBefore().equals(true)){
//        images.add(new ImageDetailResponseDto(image));
//    }


    @Override
    public Map<String, Object> requestInitialScore(MultipartFile image) {
        RESULT_OBJECT = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
            body.add("image", image.getResource());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
            HttpEntity<String> response = restTemplate.postForEntity(CORE + SCORE_IMAGE, requestMessage, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            Object object = objectMapper.readValue(response.getBody(), Object.class);
            RESULT_OBJECT.put("dict", object);
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", "IMAGE NOT SCORED");
        }
        return RESULT_OBJECT;
    }

    public List<String> requestObjectDetection(Long imageId) {
        try {
            Image img = findImage(imageId);
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();

            //받아온 imageId로 file 객체 만들기
            File file = new File(ROOT + img.getFilePath());

            //받아온 파일로 FileItem 만들기 (org.apache.commons.fileupload.FileItem)
            // DistFileItem : org.apache.commons.fileupload.disk.DiskFileItem
            FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

            try {
                IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //jpa.png -> multipart 변환
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            body.add("image", mFile.getResource());
            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
            HttpEntity<String> response = restTemplate.exchange(CORE + DETECT_OBJECT, HttpMethod.POST, requestMessage, String.class);

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

            Set<String> tags = new HashSet<>();
            for (Object obj : objects) {
                Map<String, Object> objs = objectMapper.convertValue(obj, Map.class);
                tags.add(objs.get("class").toString());
            }
            List<String> objectTag = new ArrayList<>(tags);
            img.setObjects(objectTag);
            imageRepository.save(img);


            List<String> objectList = new ArrayList<>();
            for (Object object : objects) {
                Map<String, Object> obj2 = objectMapper.convertValue(object, Map.class);
                ArrayList<String> ul = (ArrayList<String>) obj2.get("ul");
                ArrayList<String> dr = (ArrayList<String>) obj2.get("dr");
                String str = (String) obj2.get("class");
                str += ";"+String.valueOf(ul.get(0)) + "," + String.valueOf(ul.get(1)) + ";" + String.valueOf(dr.get(0)) + "," + String.valueOf(dr.get(1)) + ";";
                objectList.add(str);
            }

            return objectList;
        } catch (Exception e) {
            e.printStackTrace();
            List<String> errMessage = new ArrayList<>();
            errMessage.add("IMAGE NOT SCORED");
            return errMessage;
        }
    }

    public Map<String, Object> requestOptimizationOld(String token, MultipartFile image) {
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

    public Map<String, Object> requestOptimization(String token, Long imageId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            User user = findUserByEmail(decodeToken(token));
//             1. OptRequest 객체 저장
            OptRequest request = optRequestRepository.save(OptRequest.builder()
                    .user(user.getId())
                    .done(0)
                    .build());
//             2. MultiPartFile 재구성
            Image img = findImage(imageId);
            File file = new File(ROOT + img.getFilePath());
            FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            IOUtils.copy(Files.newInputStream(file.toPath()), fileItem.getOutputStream());

            //2-1 /app/data/buffer/userId 폴더가 있으면, 폴더 삭제
            File rootDir = new File(BUFFER + user.getId());
            if (rootDir.isDirectory()){
                try {
                    FileUtils.deleteDirectory(rootDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 3. Core로 optimization request 전달

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            body.add("image", mFile.getResource());
            body.add("userId", user.getId());
            body.add("requestId", request.getId());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
            HttpEntity<String> response = restTemplate.postForEntity(CORE + OPTIMIZE_REQUEST, requestMessage, String.class);

//             4. return 값을 reponse로 전달
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            Long requestId = objectMapper.readValue(response.getBody(), Long.class);

            RESULT_OBJECT.put("requestId", requestId);
        } catch (Exception e) {
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> optimizationProgress(Long requestId) {
        RESULT_OBJECT = new HashMap<>();
        try {
            // 1. OptRequestRepository에서 requestId로 find.finished == 7?
            if (optRequestRepository.findById(requestId).isPresent()) {
                OptRequest request = optRequestRepository.findById(requestId).get();
                if (request.getDone() >= 7) {
                    // 2. true 일 때는 score-image-by-path 요청
                    RestTemplate restTemplate = new RestTemplate();

                    ResponseEntity<String> response = restTemplate.getForEntity(
                            CORE + SCORING_PATHS + "?userId=" + request.getUser(), String.class);

                    // 2-2. 반환값 프론트로 전달
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
                    Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

                    RESULT_OBJECT.put("progress", request.getDone());
                    RESULT_OBJECT.put("dict", objects);
                } else {
                    // 3. false 일 때는 finished 개수만 전달
                    RESULT_OBJECT.put("progress", request.getDone());
                }
            } else {
                throw new Exception("REQUEST NOT FOUND");
            }
        } catch (Exception e) {
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> requestProcess(ImageOptProcessingRequestDto requestDto) {
        RESULT_OBJECT = new HashMap<>();
        try {
            System.out.println("id : " + requestDto.getRequestId());
            System.out.println("done : " + requestDto.getFinished());
            if (!optRequestRepository.findById(requestDto.getRequestId()).isPresent())
                throw new Exception("REQUEST NOT FOUND");
            OptRequest request = optRequestRepository.findById(requestDto.getRequestId()).get();
            request.update(requestDto.getFinished());
            optRequestRepository.save(request);
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> inpaintImage(InpaintingRequestDto requestDto) {
        RESULT_OBJECT = new HashMap<>();
        try {
            // 2. MultiPartFile 재구성
            Image img = findImage(requestDto.getImageId());
            File file = new File(ROOT + img.getFilePath());
            FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            IOUtils.copy(Files.newInputStream(file.toPath()), fileItem.getOutputStream());

            // 3. Core로 optimization request 전달

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            body.add("image", mFile.getResource());
            body.add("points", requestDto.getObjects());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

            HttpEntity<String> response = restTemplate.postForEntity(CORE + INPAINT_IMAGE, requestMessage, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            Object[] objects = objectMapper.readValue(response.getBody(), Object[].class);

            RESULT_OBJECT.put("dict", objects);
        } catch (Exception e) {
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Long addAfterImage(String token, AfterImageSaveRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        Long count = imageRepository.findAll().stream().count() + 1;
        Image originImage = findImage(requestDto.getImageId());
        String url = requestDto.getImageUrl();
        String filePath = url.substring(url.lastIndexOf("=") + 1);
        String originPath = originImage.getFilePath();
        String afterPath = originPath.replace("before", "after");
        String path = String.valueOf(user.getId());
        File folder = new File(ROOT + path + "/" + AFTER);

        // after 폴더 생성
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        // 파일 복사
        File img = new File(filePath);
        File newFile = new File(ROOT + afterPath);
        FileUtils.copyFile(img, newFile);

        // 파일 삭제
        try {
            File rootDir = new File(BUFFER + path);
            FileUtils.deleteDirectory(rootDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageRepository.save(requestDto.toDocument(user.getId(), ADDRESS + count.toString(), afterPath, originImage.getObjects())).getId();
    }

    public Long addCsvData(ImageSaveRequestDto requestDto){
        return dataRepository.save(requestDto.toDocument()).getId();
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
