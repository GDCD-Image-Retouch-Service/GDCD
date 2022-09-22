package com.gdcd.back.controller.image;

import com.gdcd.back.controller.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController extends Controller {

//    private final ImageRepository imageRepository;

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

    @PostMapping("/storage")
    public ResponseEntity<Map<String, Object>> imageSave() {
        return getResponseEntity("hi");
    }

    @PostMapping("/datafication")
    public ResponseEntity<Map<String, Object>> imageDatafication() {
        return getResponseEntity("hi");
    }
}
