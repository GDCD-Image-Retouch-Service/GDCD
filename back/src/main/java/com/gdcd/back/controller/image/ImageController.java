package com.gdcd.back.controller.image;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.image.request.ImageCreateRequestDto;
import com.gdcd.back.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController extends Controller {
    private final ImageService imageService;
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> imageSave(@RequestHeader String token, @RequestPart MultipartFile image,  @RequestPart(required = false) ImageCreateRequestDto requestDto) throws Exception {
        return getResponseEntity(imageService.addImage(token, image, requestDto));
    }

    @GetMapping(value="", produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> imageDetails(@RequestParam(required = false) Long imageId, @RequestParam(required = false) String from) throws IOException {
        return new ResponseEntity<byte[]>(imageService.findImageById(imageId,from), HttpStatus.OK);
    }

    @GetMapping(value = "/info")
    public ResponseEntity<Map<String,Object>> imageDetailInfo(@RequestParam Long imageId){
        return getResponseEntity(imageService.findImageInfoById(imageId));
    }
//    @GetMapping(path = "")
//    public String setImageFileById(@RequestParam Long imageId, HttpServletResponse response)
//            throws IOException {
//
//        return "file:///C:/test/images/Test/1.jpg";
//
//    }

    @GetMapping(value="/list")
//    @ResponseBody
    public ResponseEntity<Map<String, Object>> imageList(@RequestHeader String token) throws Exception {
        return getResponseEntity(imageService.findImageList(token));
    }
//    @GetMapping(value="/list")
//    //    @ResponseBody
//    public ResponseEntity<Map<String, Object>> imageList(@RequestParam Long userId) throws Exception {
//        return getResponseEntity(imageService.findImageList(userId));
//    }

//    @GetMapping(value="/list", produces = List<MediaType.IMAGE_JPEG_VALUE>)
//    public ResponseEntity<List<String>> imageList(@RequestParam Long userId) throws IOException {
//        return new ResponseEntity<List<String>>(imageService.findImageList(userId), HttpStatus.OK);
//    }

    @PostMapping("/initial")
    public ResponseEntity<Map<String, Object>> imageInitialScore(@RequestPart MultipartFile image) {
        return getResponseEntity(imageService.requestInitialScore(image));
    }

    @PostMapping("/object")
    public ResponseEntity<Map<String, Object>> imageObjection(@RequestPart MultipartFile image) {
        return getResponseEntity(imageService.requestObjectDetection(image));
    }

    @PostMapping("/optimization")
    public ResponseEntity<Map<String, Object>> imageOptimization(@RequestHeader String token, @RequestPart MultipartFile image) {
        return getResponseEntity(imageService.requestOptimization(token, image));
    }

//    @PostMapping("/storage")
//    public ResponseEntity<Map<String, Object>> imageSave() {
//        return getResponseEntity("hi");
//    }


    @PostMapping("/datafication")
    public ResponseEntity<Map<String, Object>> imageDatafication() {
        return getResponseEntity("hi");
    }
}
