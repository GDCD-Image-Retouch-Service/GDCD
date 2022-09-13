package com.gdcd.back.controller.user;

import com.gdcd.back.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public void userLogin() {}

    @GetMapping("/nickname")
    public void nicknameCheck() {}

    @GetMapping("")
    public void userDetails() {}

    @PutMapping("")
    public void userModify() {}

    @DeleteMapping("")
    public void userRemove() {}

    @GetMapping("/block")
    public void userBlockSave() {}

    @GetMapping("/scrap-list")
    public void userScrapList() {}

    @GetMapping("/like-list")
    public void userLikeList() {}

    @GetMapping("/follow")
    public void userFollowSave() {}

    @GetMapping("/follower")
    public void userFollowerList() {}

    @GetMapping("/following")
    public void userFollowingList() {}
}
