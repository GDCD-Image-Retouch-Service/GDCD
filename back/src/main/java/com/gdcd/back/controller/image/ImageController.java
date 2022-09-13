package com.gdcd.back.controller.image;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

//    private final ImageRepository imageRepository;

    @PostMapping("/initial")
    public void imageInitialScore() {}

    @PostMapping("/object")
    public void imageObjection() {}

    @PostMapping("/optimization")
    public void imageOptimization() {}

    @PostMapping("/storage")
    public void imageSave() {}

    @PostMapping("/datafication")
    public void imageDatafication() {}
}
