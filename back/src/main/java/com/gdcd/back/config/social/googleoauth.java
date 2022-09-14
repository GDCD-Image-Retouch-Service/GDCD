package com.gdcd.back.config.social;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class googleoauth implements socialoauth {
    @Override
    public String getOauthRedirectURL(){
        return "";}
}