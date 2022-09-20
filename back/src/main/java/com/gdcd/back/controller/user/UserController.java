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
//        //Nickname 중복 체크
        HashMap<String, Object> result = new HashMap<>();
        if (userService.checkNickname(nickname)) {
            result.put("msg", FAIL);
        } else {
            result.put("msg", SUCCESS);
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

//    @GetMapping("/id")
//    public ResponseEntity<Map<String, Object>> checkuserId(@RequestParam Object userId) {
//        return getResponseEntity(userRepository.)
//        return getResponseEntity(userRepository.findByNickname(nickname));
//        return getResponseEntity(userService.checkuserId(userId));
//        return getResponseEntity(userRepository.findBy_id(userId));
//    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> userDetails(@RequestParam Long userId) {
        return getResponseEntity(userService.findUserById(userId));
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> userModify(@RequestParam Long userId, @RequestBody UserDetailUpdateRequestDto requestDto) {
        return getResponseEntity(userService.modifyUser(userId, requestDto));
    }

    @DeleteMapping("")
    public ResponseEntity<Map<String, Object>> userRemove(@RequestParam Long userId) {
        return getResponseEntity(userService.removeUser(userId));
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
