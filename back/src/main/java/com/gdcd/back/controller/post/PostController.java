package com.gdcd.back.controller.post;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.post.request.*;
import com.gdcd.back.service.post.CommentService;
import com.gdcd.back.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController extends Controller {
    private final PostService postService;
    private final CommentService commentService;
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> postList() {
        return getResponseEntity(postService.findPosts());
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> postDetails(@RequestParam Long postId) {
        return getResponseEntity(postService.findPostById(postId));
    }

//    @PostMapping("")
//    public ResponseEntity<Map<String, Object>> postAdd(@RequestPart("requestDto") PostCreateRequestDto requestDto) {
//        return getResponseEntity(postService.addPost(requestDto));
////        return getResponseEntity(images);
//    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> postAdd(@RequestPart("requestDto") PostCreateRequestDto requestDto) throws IOException {
        return getResponseEntity(postService.addPost(requestDto));
//        return getResponseEntity(images);
    }

//    @PostMapping("")
//    public String postAdd(@RequestPart MultipartFile images, @RequestBody PostCreateRequestDto requestDto) {
////        return getResponseEntity(postService.addPost(requestDto));
//        return images.getOriginalFilename();
//    }

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
    public ResponseEntity<Map<String, Object>> commentList(@RequestParam Long postId) {
        return getResponseEntity(commentService.findComments(postId));
    }

    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentAdd(@RequestBody CommentCreateRequestDto requestDto) {
        return getResponseEntity(commentService.addComment(requestDto));
    }

    @PutMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentModify(@RequestBody CommentUpdateRequestDto requestDto) {
        return getResponseEntity(commentService.modifyComment(requestDto));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentDelete(@RequestParam Long commentId) {
        return getResponseEntity(commentService.deleteComment(commentId));
    }
}
