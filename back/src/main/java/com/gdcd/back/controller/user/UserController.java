package com.gdcd.back.controller.user;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends Controller {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin() {
        return getResponseEntity("hi");
    }

    @GetMapping("/nickname")
    public ResponseEntity<Map<String, Object>> nicknameCheck() {
        return getResponseEntity("hi");
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> userDetails() {
        return getResponseEntity("hi");
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> userModify() {
        return getResponseEntity("hi");
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> userRemove() {
        return getResponseEntity("hi");
    }

    @GetMapping("/block")
    public ResponseEntity<Map<String, Object>> userBlockSave() {
        return getResponseEntity("hi");
    }

    @GetMapping("/scrap-list")
    public ResponseEntity<Map<String, Object>> userScrapList() {
        return getResponseEntity("hi");
    }

    @GetMapping("/like-list")
    public ResponseEntity<Map<String, Object>> userLikeList() {
        return getResponseEntity("hi");
    }

    @GetMapping("/follow")
    public ResponseEntity<Map<String, Object>> userFollowSave() {
        return getResponseEntity("hi");
    }

    @GetMapping("/follower")
    public ResponseEntity<Map<String, Object>> userFollowerList() {
        return getResponseEntity("hi");
    }

    @GetMapping("/following")
    public ResponseEntity<Map<String, Object>> userFollowingList() {
        return getResponseEntity("hi");
    }
}
