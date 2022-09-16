package com.gdcd.back.controller.post;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.post.request.PostCreateRequestDto;
import com.gdcd.back.dto.post.request.PostReportRequestDto;
import com.gdcd.back.dto.post.request.PostUpdateRequestDto;
import com.gdcd.back.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController extends Controller {

//    private final PostRepository postRepository;
    private final PostService postService;
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> postList() {
        return getResponseEntity(postService.findPosts());
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> postDetails(@RequestParam Long postId) {
        return getResponseEntity(postService.findPostById(postId));
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> postAdd(@RequestBody PostCreateRequestDto requestDto) {
        return getResponseEntity(postService.addPost(requestDto));
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> postModify(@RequestBody PostUpdateRequestDto requestDto) {
        return getResponseEntity(postService.modifyPost(requestDto));
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> postDelete(@RequestParam Long postId) {
        return getResponseEntity(postService.removePost(postId));
    }

    @GetMapping("/like")
    public ResponseEntity<Map<String, Object>> postLikeSave(@RequestParam Long postId) {
        return getResponseEntity(postService.likePost(postId));
    }

    @GetMapping("/scrap")
    public ResponseEntity<Map<String, Object>> postScrapSave(@RequestParam Long postId) {
        return getResponseEntity(postService.scrapPost(postId));
    }

    @PostMapping("/report")
    public ResponseEntity<Map<String, Object>> postReportSave(@RequestBody PostReportRequestDto requestDto) {
        return getResponseEntity(postService.reportPost(requestDto));
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
