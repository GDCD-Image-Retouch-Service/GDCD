package com.gdcd.back.service.image;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdcd.back.config.JwtTokenProvider;
import com.gdcd.back.domain.image.Image;
import com.gdcd.back.domain.image.ImageRepository;
import com.gdcd.back.domain.image.data.DataRepository;
import com.gdcd.back.domain.image.optrequest.OptRequest;
import com.gdcd.back.domain.image.optrequest.OptRequestRepository;
import com.gdcd.back.domain.sequence.ImageSequence;
import com.gdcd.back.domain.sequence.ImageSequenceRepository;
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
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OptRequestRepository optRequestRepository;
    private final ImageSequenceRepository imageSequenceRepository;
    private final DataRepository dataRepository;
    private final String ROOT = "/app/data/images/";
    private final String ADDRESS = "https://j7b301.p.ssafy.io/api/image?imageId=";
    private final String CORE = "https://j7b301.p.ssafy.io/core/";
    private final String SCORE_IMAGE = "score-image";
    private final String DETECT_OBJECT = "detect-object";
    private final String OPTIMIZE_IMAGE = "optimize-image";
    private final String OPTIMIZE_REQUEST = "optimize-request";
    private final String SCORING_IMAGE_BY_USER_ID = "score-image-by-user-id";
    private final String INPAINT_IMAGE = "inpaint-image";
    private final String BEFORE = "/before";
    private final String AFTER = "/after";
    private final String BUFFER = "/app/data/buffer/";
    private Map<String, Object> RESULT_OBJECT;
    private final float[] AESTHETIC_STANDARD = {3.5236737f, 4.7591897f, 4.9708054f, 5.1132074f, 5.2307573f, 5.3364713f, 5.4399255f, 5.5490340f, 5.6771072f, 5.8493036f};
    private final float[] QUALITY_STANDARD = {3.9448474f, 6.4792895f, 6.7779994f, 6.8344460f, 6.8756410f, 6.9097216f, 6.9412602f, 6.9728014f, 7.0068225f, 7.0490238f};

//        Local Path
//        private final String ROOT = "C:/test/images/";
//        private final String BUFFER = "C:/test/buffer/";
//        private final String ADDRESS = "http://localhost:8081/api/image?imageId=";

    public Long addImage(String token, MultipartFile image, ImageCreateRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        Long urlCount = imageRepository.findAllByUserId(user.getId()).stream().count() + 1;
        String imageType = image.getContentType();
        String endPoint = "." + imageType.substring(imageType.lastIndexOf("/") + 1);
        String path = user.getId().toString();
        String urlName = "/" + urlCount + endPoint;
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
            Long count = imageSequenceRepository.findById("image_sequences").get().getSeq()+1;
            requestDto.setImgUrl(ADDRESS + count);
            requestDto.setUserId(user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageRepository.save(requestDto.toDocument()).getId();
    }

    public byte[] findImageById(Long imageId, String from) throws IOException {
        InputStream imageStream;
        if (from == null)
            imageStream = Files.newInputStream(Paths.get(ROOT + findImage(imageId).getFilePath()));
        else
            imageStream = Files.newInputStream(Paths.get(from));
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return imageByteArray;
    }

    public ImageDetailResponseDto findImageInfoById(Long imageId) {
        Image img = findImage(imageId);
        return new ImageDetailResponseDto(img);
    }

    public Map<LocalDate, List<ImageListResponseDto>> findImageList(String token, Pageable pageable) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        List<Image> imageList = imageRepository.findAllByUserIdAndBeforeImage(user.getId(), true);

        //SET으로 중복 제거
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
                    Optional<Image> afterImage = imageRepository.findByFilePath(image.getFilePath().replace("before", "after"));
                    if (afterImage.isPresent()) {
                        list.add(new ImageListResponseDto(new ImageDetailResponseDto(image), new ImageDetailResponseDto(afterImage.get())));
                    } else {
                        list.add(new ImageListResponseDto(new ImageDetailResponseDto(image), null));
                    }
                }
            }
            images.put(datetime, list);
            System.out.println(datetime);
        }
        return images;
    }

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

            // 받아온 imageId로 file 객체 만들기
            File file = new File(ROOT + img.getFilePath());

            // 받아온 파일로 FileItem 만들기 (org.apache.commons.fileupload.FileItem)
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

            // header
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            // body
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", image.getResource());
            body.add("user_id", findUserByEmail(decodeToken(token)).getId());

            // requestMessage
            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

            // request
            HttpEntity<String> response = restTemplate.postForEntity(CORE + OPTIMIZE_IMAGE, requestMessage, String.class);

            // response
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
            // 1. OptRequest 객체 저장
            OptRequest request = optRequestRepository.save(OptRequest.builder()
                    .user(user.getId())
                    .done(0)
                    .build());
            // 2. MultiPartFile 재구성
            Image img = findImage(imageId);
            File file = new File(ROOT + img.getFilePath());
            FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            IOUtils.copy(Files.newInputStream(file.toPath()), fileItem.getOutputStream());

            // 2-1. /app/data/buffer/userId 폴더가 있으면, 폴더 삭제
            File rootDir = new File(BUFFER + user.getId());
            if (rootDir.isDirectory())
                FileUtils.deleteDirectory(rootDir);

            // 3. Core로 optimization request 전달
            RestTemplate restTemplate = new RestTemplate();

            // 3-1. header
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            // 3-2. body
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            body.add("image", mFile.getResource());
            body.add("userId", user.getId());
            body.add("requestId", request.getId());

            // 3-3. requestMessage
            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

            //3-4 buffer/{user_Id} 있다면 , 폴더 삭제
            String path = String.valueOf(user.getId());
            try {
                File BufferDir = new File(BUFFER + path);
                FileUtils.deleteDirectory(BufferDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 3-5. request
            HttpEntity<String> response = restTemplate.postForEntity(CORE + OPTIMIZE_REQUEST, requestMessage, String.class);

            // 3-6. parse response
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
                            CORE + SCORING_IMAGE_BY_USER_ID + "?userId=" + request.getUser(), String.class
                    );

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
            if (!optRequestRepository.findById(requestDto.getRequestId()).isPresent())
                throw new Exception("REQUEST NOT FOUND");
            OptRequest request = optRequestRepository.findById(requestDto.getRequestId()).get();
            request.update(requestDto.getFinished());
            optRequestRepository.save(request);
        } catch (Exception e) {
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Map<String, Object> inpaintImage(String token, InpaintingRequestDto requestDto) {
        try {
            // 1. front2api : request
            User user = findUserByEmail(decodeToken(token));
            Image img = findImage(requestDto.getImageId());
            File file = new File(ROOT + img.getFilePath());
            FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            IOUtils.copy(Files.newInputStream(file.toPath()), fileItem.getOutputStream());

            // 2. api2core : inpaint request
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            MultipartFile mFile = new CommonsMultipartFile(fileItem);
            body.add("image", mFile.getResource());
            body.add("points", requestDto.getObjects().toString());
            body.add("userId", user.getId());

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

            HttpEntity<String> response = restTemplate.postForEntity(CORE + INPAINT_IMAGE, requestMessage, String.class);

            // 3. core2api : inpaint response
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            String inpaintedImageRequestSrc = objectMapper.readValue(response.getBody(), String.class);
            // "https://j7b301.p.ssafy.io/api/image?from=/app/data/buffer/1/inpainting.png"

            String inpaintedImagePath = inpaintedImageRequestSrc.substring(inpaintedImageRequestSrc.lastIndexOf("=") + 1);
            // "/app/data/buffer/1/inpainting.png"
            File inpaintedImagefile = new File(inpaintedImagePath);
            FileItem inpaintedImagefileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            IOUtils.copy(Files.newInputStream(inpaintedImagefile.toPath()), inpaintedImagefileItem.getOutputStream());

            // 4. api2core : image score request
            // path > multipartfile 생성
            mFile = new CommonsMultipartFile(inpaintedImagefileItem);
            Map<String, Object> scoreResponse = requestInitialScore(mFile);

            // 5. core2api : image score response
            String temp = scoreResponse.get("dict").toString();
            float aes = Float.parseFloat(temp.substring(temp.indexOf("aesthetic=") + 10, temp.indexOf(",")));
            float qua = Float.parseFloat(temp.substring(temp.indexOf("quality=") + 8, temp.indexOf("}")));
            int aesRank = 1;
            int quaRank = 1;

            for (int i = 0; i < AESTHETIC_STANDARD.length; i++) {
                if (aes < AESTHETIC_STANDARD[i]) {
                    aesRank = 9 - i;
                    break;
                }
            }
            for (int i = 0; i < QUALITY_STANDARD.length; i++) {
                if (qua < QUALITY_STANDARD[i]) {
                    quaRank = 9 - i;
                    break;
                }
            }

            // 6. api2db&server : imageSave
            Long afterImageId = addAfterImage(token, AfterImageSaveRequestDto.builder()
                    .imageId(requestDto.getImageId()) // original image id
                    .imageUrl(inpaintedImageRequestSrc) // new image url rule ...
                    .aesthetic(aesRank)
                    .quality(quaRank)
                    .build());

            // 7. api2core : object detect request
            // 8. core2api : object detect response
            // 9. api2db : image tag update
            requestObjectDetection(afterImageId);

            // 10. api2front : response
            RESULT_OBJECT = new HashMap<>();
            RESULT_OBJECT.put("image", new ImageDetailResponseDto(findImage(afterImageId)));
        } catch (Exception e) {
            RESULT_OBJECT = new HashMap<>();
            RESULT_OBJECT.put("error", e.getMessage());
        }
        return RESULT_OBJECT;
    }

    public Long addAfterImage(String token, AfterImageSaveRequestDto requestDto) throws Exception {
        User user = findUserByEmail(decodeToken(token));
        Long count = imageSequenceRepository.findById("image_sequences").get().getSeq()+1;
        Image originImage = findImage(requestDto.getImageId());
        String url = requestDto.getImageUrl();
        String filePath = url.substring(url.lastIndexOf("=") + 1);
        String originPath = originImage.getFilePath();
        String afterPath = originPath.replace("before", "after");
        if (imageRepository.findByFilePath(afterPath).isPresent()){
            imageRepository.delete(imageRepository.findByFilePath(afterPath).get());
        }
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

    public Long addCsvData(ImageSaveRequestDto requestDto) {
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

    private String decodeToken(String token) {
        return jwtTokenProvider.decodeToken(token);
    }
}
