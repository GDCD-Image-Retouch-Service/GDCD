package com.gdcd.back.service.user;

import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.UserRepository;
import com.gdcd.back.dto.user.request.UserDetailUpdateRequestDto;
import com.gdcd.back.dto.user.response.UserDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails findUserForToken(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public Boolean checkNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
//        return userRepository.findBy_Id()
    }
//    public Object checkuserId(Object userId){
//        Object user = userRepository.findByid(userId);
//        System.out.println(user);
//        return user;
//    }
    public UserDetailResponseDto findUserById(Long userId){
        User user = findUser(userId);
        if (validUser(user)){
           return new UserDetailResponseDto(user);
        }else {
            return null;
        }
    }

    public UserDetailResponseDto modifyUser(Long userId, UserDetailUpdateRequestDto requestDto){
        User user = findUser(userId);
        if (validUser(user)){
            user.update(
                    requestDto.getProfile(),
                    requestDto.getNickname()
            );
            userRepository.save(user);
            return new UserDetailResponseDto(user);
        }else {
            return null;
        }
//        if(!userRepository.save(user).getNickname().isEmpty()){
//            return true;
//        }else {
//            return false;
//        }
    }

    public String removeUser(Long userId){
        User user = findUser(userId);
        if (validUser(user)){
            user.setValidation(false);
            userRepository.save(user);
            return "성공적으로 삭제되었습니다.";
        }else {
            return "존재하지 않는 유저입니다";
        }
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(userId + "은(는) 존재하지 않는 유저입니다."));
    }

    private Boolean validUser(User user){
        if (user.getValidation().equals(true)){
            return true;
        }else {
            return false;
        }
    }

}