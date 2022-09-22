package com.gdcd.back.controller.user;

import com.gdcd.back.controller.Controller;
import com.gdcd.back.dto.user.request.UserCreateRequestDto;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends Controller {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
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
    public ResponseEntity<Map<String, Object>> userDetails(@RequestHeader(required = false) String token, @RequestParam(required = false) Long userId) throws Exception {
        return getResponseEntity(userService.findUser(token, userId));
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> userModify(@RequestHeader String token, @RequestBody UserDetailUpdateRequestDto requestDto) throws Exception{
        return getResponseEntity(userService.modifyUser(token, requestDto));
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> userRemove(@RequestHeader String token) throws Exception{
        return getResponseEntity(userService.removeUser(token));
    }

    @GetMapping("/block")
    public ResponseEntity<Map<String, Object>> userBlockSave(@RequestHeader String token, @RequestParam Long userId) {
        return getResponseEntity(userService.blockUser(token, userId));
    }

    @DeleteMapping("/block")
    public ResponseEntity<Map<String, Object>> userBlockRemove(@RequestParam Long blockId) {
        return getResponseEntity(userService.cancleBlock(blockId));
    }

    @GetMapping("/scrap-list")
    public ResponseEntity<Map<String, Object>> userScrapList(@RequestHeader String token) {
        return getResponseEntity(userService.findScraps(token));
    }

    @GetMapping("/like-list")
    public ResponseEntity<Map<String, Object>> userLikeList(@RequestHeader String token) {
        return getResponseEntity(userService.findLikes(token));
    }

    @GetMapping("/follow")
    public ResponseEntity<Map<String, Object>> userFollowSave(@RequestHeader String token, @RequestParam Long userId) {
        return getResponseEntity(userService.followUser(token, userId));
    }

    @GetMapping("/follower")
    public ResponseEntity<Map<String, Object>> userFollowerList(@RequestHeader String token) {
        return getResponseEntity(userService.findFollowers(token));
    }

    @GetMapping("/following")
    public ResponseEntity<Map<String, Object>> userFollowingList(@RequestHeader String token) {
        return getResponseEntity(userService.findFollowings(token));
    }
}
