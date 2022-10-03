package com.gdcd.back.controller.post;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.post.request.*;
import com.gdcd.back.service.post.CommentService;
import com.gdcd.back.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Map<String, Object>> postList(@RequestHeader String token, Pageable pageable) throws Exception {
        return getResponseEntity(postService.findPosts(token, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> postListByUser(@RequestHeader String token, @RequestParam(required = false) Long userId, Pageable pageable) throws Exception{
        return getResponseEntity(postService.findPostsByUser(token, userId, pageable));
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> postDetails(@RequestHeader String token, @RequestParam Long postId) throws Exception {
        return getResponseEntity(postService.findPostById(token, postId));
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> postAdd(@RequestHeader String token, @RequestBody PostCreateRequestDto requestDto) throws Exception {
        return getResponseEntity(postService.addPost(token, requestDto));
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> postModify(@RequestHeader String token, @RequestBody PostUpdateRequestDto requestDto) throws Exception {
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
    public ResponseEntity<Map<String, Object>> commentList(@RequestHeader String token, @RequestParam Long postId, Pageable pageable) {
        return getResponseEntity(commentService.findComments(token, postId, pageable));
    }

    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentAdd(@RequestHeader String token, @RequestBody CommentCreateRequestDto requestDto) {
        return getResponseEntity(commentService.addComment(token, requestDto));
    }

    @PutMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentModify(@RequestHeader String token, @RequestBody CommentUpdateRequestDto requestDto) {
        return getResponseEntity(commentService.modifyComment(token, requestDto));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Map<String, Object>> commentDelete(@RequestHeader String token, @RequestParam Long commentId) {
        return getResponseEntity(commentService.deleteComment(token, commentId));
    }
}
