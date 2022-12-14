package com.gdcd.back.controller.image;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.image.request.*;
import com.gdcd.back.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Map<String, Object>> imageSave(@RequestHeader String token, @RequestPart MultipartFile image,  @RequestPart(required = false) int aesthetic, @RequestPart(required = false) int quality) throws Exception {
        return getResponseEntity(imageService.addImage(token, image, ImageCreateRequestDto.builder()
                        .aesthetic(aesthetic).quality(quality)
                .build()
        ));
    }

    @GetMapping(value="", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> imageDetails(@RequestParam(required = false) Long imageId, @RequestParam(required = false) String from) throws IOException {
        return new ResponseEntity<byte[]>(imageService.findImageById(imageId,from), HttpStatus.OK);
    }

    @GetMapping(value = "/info")
    public ResponseEntity<Map<String,Object>> imageDetailInfo(@RequestParam Long imageId){
        return getResponseEntity(imageService.findImageInfoById(imageId));
    }

    @GetMapping(value="/list")
    public ResponseEntity<Map<String, Object>> imageList(@RequestHeader String token, Pageable pageable) throws Exception {
        return getResponseEntity(imageService.findImageList(token, pageable));
    }

    @PostMapping("/scoring")
    public ResponseEntity<Map<String, Object>> imageInitialScore(@RequestPart MultipartFile image) {
        return getResponseEntity(imageService.requestInitialScore(image));
    }

    @GetMapping("/object")
    public ResponseEntity<Map<String, Object>> imageObjection(@RequestParam Long imageId) {
        return getResponseEntity(imageService.requestObjectDetection(imageId));
    }

    @PostMapping("/optimization-old")
    public ResponseEntity<Map<String, Object>> imageOptimizationOld(@RequestHeader String token, @RequestPart MultipartFile image) {
        return getResponseEntity(imageService.requestOptimizationOld(token, image));
    }

    @GetMapping("/optimization")
    public ResponseEntity<Map<String, Object>> imageOptimization(@RequestHeader String token, @RequestParam Long imageId) {
        return getResponseEntity(imageService.requestOptimization(token, imageId));
    }

    @GetMapping("/process")
    public ResponseEntity<Map<String, Object>> optimizationProcess(@RequestParam Long requestId) {
        return getResponseEntity(imageService.optimizationProgress(requestId));
    }

    @GetMapping("/request-process")
    public ResponseEntity<Map<String, Object>> requestProcess(@RequestParam Long requestId, @RequestParam int finished) {
        return getResponseEntity(imageService.requestProcess(ImageOptProcessingRequestDto.builder()
                        .requestId(requestId)
                        .finished(finished)
                .build()));
    }

    @PostMapping("/inpainting")
    public ResponseEntity<Map<String, Object>> inpainting(@RequestHeader String token, @RequestBody InpaintingRequestDto requestDto) {
        return getResponseEntity(imageService.inpaintImage(token, requestDto));
    }

    @PostMapping("/storage")
    public ResponseEntity<Map<String, Object>> afterImageSave(@RequestHeader String token, @RequestBody AfterImageSaveRequestDto requestDto) throws Exception {
        return getResponseEntity(imageService.addAfterImage(token,requestDto));
    }

    @PostMapping("/datafication")
    public ResponseEntity<Map<String, Object>> imageDatafication(@RequestBody ImageSaveRequestDto requestDto) {
        return getResponseEntity(imageService.addCsvData(requestDto));
    }
}
