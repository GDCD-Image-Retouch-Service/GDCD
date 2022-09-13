package com.gdcd.back.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

//    private final PostRepository postRepository;

    @GetMapping("/list")
    public void postList() {}

    @GetMapping("")
    public void postDetails() {}

    @PostMapping("")
    public void postAdd() {}

    @PutMapping("")
    public void postModify() {}

    @DeleteMapping("")
    public void postDelete() {}

    @GetMapping("/like")
    public void postLikeSave() {}

    @GetMapping("/scrap")
    public void postScrapSave() {}

    @PostMapping("/report")
    public void postReportSave() {}

    @GetMapping("/comment")
    public void commentList() {}

    @PostMapping("/comment")
    public void commentAdd() {}

    @PutMapping("/comment")
    public void commentModify() {}

    @DeleteMapping("/comment")
    public void commentDelete() {}
}
