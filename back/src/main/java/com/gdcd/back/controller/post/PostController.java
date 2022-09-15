package com.gdcd.back.controller.post;

import com.gdcd.back.controller.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController extends Controller {

//    private final PostRepository postRepository;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> postList() {
        return getResponseEntity("hi");
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> postDetails() {
        return getResponseEntity("hi");
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> postAdd() {
        return getResponseEntity("hi");
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> postModify() {
        return getResponseEntity("hi");
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> postDelete() {
        return getResponseEntity("hi");
    }

    @GetMapping("/like")
    public ResponseEntity<Map<String, Object>> postLikeSave() {
        return getResponseEntity("hi");
    }

    @GetMapping("/scrap")
    public ResponseEntity<Map<String, Object>> postScrapSave() {
        return getResponseEntity("hi");
    }

    @PostMapping("/report")
    public ResponseEntity<Map<String, Object>> postReportSave() {
        return getResponseEntity("hi");
    }

    @GetMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentList() {
        return getResponseEntity("hi");
    }

    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentAdd() {
        return getResponseEntity("hi");
    }

    @PutMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentModify() {
        return getResponseEntity("hi");
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentDelete() {
        return getResponseEntity("hi");
    }
}
