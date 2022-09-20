package com.gdcd.back.controller.image;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController extends Controller {

//    private final ImageRepository imageRepository;
    private final ImageService imageService;
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> imageSave(@RequestPart MultipartFile image) throws IOException {
        return getResponseEntity(imageService.addImage(image));
    }

    @GetMapping(value="", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> imageDetails(@RequestParam Long imageId) throws IOException {
        return new ResponseEntity<byte[]>(imageService.findImageById(imageId), HttpStatus.OK);
    }


    @PostMapping("/initial")
    public ResponseEntity<Map<String, Object>> imageInitialScore() {
        return getResponseEntity("hi");
    }

    @PostMapping("/object")
    public ResponseEntity<Map<String, Object>> imageObjection() {
        return getResponseEntity("hi");
    }

    @PostMapping("/optimization")
    public ResponseEntity<Map<String, Object>> imageOptimization() {
        return getResponseEntity("hi");
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
