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
    public ResponseEntity<Map<String, Object>> postList(@RequestHeader String token) throws Exception {
        return getResponseEntity(postService.findPosts(token));
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> postDetails(@RequestHeader String token, @RequestParam Long postId) throws Exception {
        return getResponseEntity(postService.findPostById(token, postId));
    }

//    @PostMapping("")
//    public ResponseEntity<Map<String, Object>> postAdd(@RequestPart("requestDto") PostCreateRequestDto requestDto) {
//        return getResponseEntity(postService.addPost(requestDto));
////        return getResponseEntity(images);
//    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> postAdd(@RequestHeader String token, @RequestBody PostCreateRequestDto requestDto) throws Exception {
        return getResponseEntity(postService.addPost(token, requestDto));
//        return getResponseEntity(images);
    }

//    @PostMapping("")
//    public String postAdd(@RequestPart MultipartFile images, @RequestBody PostCreateRequestDto requestDto) {
////        return getResponseEntity(postService.addPost(requestDto));
//        return images.getOriginalFilename();
//    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> postModify(@RequestHeader String token, @RequestBody PostUpdateRequestDto requestDto) {
        return getResponseEntity(postService.modifyPost(token, requestDto));
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> postDelete(@RequestHeader String token, @RequestParam Long postId) throws Exception{
        return getResponseEntity(postService.removePost(token, postId));
    }

    @GetMapping("/like")
    public ResponseEntity<Map<String, Object>> postLikeSave(@RequestHeader String token, @RequestParam Long postId) throws Exception {
        return getResponseEntity(postService.likePost(token, postId));
    }

    @GetMapping("/scrap")
    public ResponseEntity<Map<String, Object>> postScrapSave(@RequestHeader String token, @RequestParam Long postId) throws Exception {
        return getResponseEntity(postService.scrapPost(token, postId));
    }

    @PostMapping("/report")
    public ResponseEntity<Map<String, Object>> postReportSave(@RequestHeader String token, @RequestBody PostReportRequestDto requestDto) throws Exception{
        return getResponseEntity(postService.reportPost(token, requestDto));
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
