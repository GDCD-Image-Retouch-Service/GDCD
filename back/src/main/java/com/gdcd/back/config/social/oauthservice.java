package com.gdcd.back.config.social;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class oauthservice {
    @Service
    @RequiredArgsConstructor
    public class OAuthService {
        private final googleoauth googleOauth;
        private final HttpServletResponse response;

        public void request(constant.SocialLoginType socialLoginType) throws IOException {
            String redirectURL;
            switch (socialLoginType){
                case GOOGLE:{
                    //각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 프로세스이다.
                    redirectURL= googleOauth.getOauthRedirectURL();
                }break;
                default:{
                    throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
                }

            }

            response.sendRedirect(redirectURL);
        }
    }
}
