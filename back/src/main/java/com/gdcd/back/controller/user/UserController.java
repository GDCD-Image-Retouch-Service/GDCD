package com.gdcd.back.controller.user;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends Controller {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody UserCreateRequestDto requestDto) {
        return getResponseEntity(userService.loginUser(requestDto));
    }

    @GetMapping("/nickname")
    public ResponseEntity<Map<String, Object>> nicknameCheck(@RequestParam String nickname) {
        return getResponseEntity(userService.checkNickname(nickname));
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> userDetails(@RequestHeader(required = false) String token, @RequestParam(required = false) Long userId) {
        return getResponseEntity(userService.findUser(token, userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> profileModify(@RequestHeader String token, @RequestPart(required = false) MultipartFile profile) {
        return getResponseEntity(userService.modifyProfile(token, profile));
    }

    @PutMapping("/nickname")
    public ResponseEntity<Map<String, Object>> nicknameModify(@RequestHeader String token, @RequestParam(required = false) String nickname) {
        return getResponseEntity(userService.modifyNickname(token, nickname));
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> userRemove(@RequestHeader String token) {
        return getResponseEntity(userService.removeUser(token));
    }

    @GetMapping("/block")
    public ResponseEntity<Map<String, Object>> userBlockSave(@RequestHeader String token, @RequestParam Long userId) {
        return getResponseEntity(userService.blockUser(token, userId));
    }

    @GetMapping("/scrap-list")
    public ResponseEntity<Map<String, Object>> userScrapList(@RequestHeader String token, @RequestParam(required = false) Long userId, Pageable pageable) {
        return getResponseEntity(userService.findScraps(token, userId, pageable));
    }

    @GetMapping("/like-list")
    public ResponseEntity<Map<String, Object>> userLikeList(@RequestHeader String token, @RequestParam(required = false) Long userId, Pageable pageable) {
        return getResponseEntity(userService.findLikes(token, userId, pageable));
    }

    @GetMapping("/follow")
    public ResponseEntity<Map<String, Object>> userFollowSave(@RequestHeader String token, @RequestParam Long userId) {
        return getResponseEntity(userService.followUser(token, userId));
    }

    @GetMapping("follow-check")
    public ResponseEntity<Map<String, Object>> userFollowCheck(@RequestHeader String token, @RequestParam Long userId) {
        return getResponseEntity(userService.followCheck(token, userId));
    }

    @GetMapping("/follower")
    public ResponseEntity<Map<String, Object>> userFollowerList(@RequestHeader String token, @RequestParam(required = false) Long userId, Pageable pageable) {
        return getResponseEntity(userService.findFollowers(token, userId, pageable));
    }

    @GetMapping("/following")
    public ResponseEntity<Map<String, Object>> userFollowingList(@RequestHeader String token, @RequestParam(required = false) Long userId, Pageable pageable) {
        return getResponseEntity(userService.findFollowings(token, userId, pageable));
    }

    @GetMapping(value = "/profile", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userProfile(@RequestParam String from) {
        return new ResponseEntity<>(userService.findProfile(from), HttpStatus.OK);
    }
}
