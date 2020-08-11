package com.kiheyunkim.login;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final HttpSession httpSession;

    public CustomOauth2UserService(HttpSession httpSession){
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();        //받아줄 객체 생성
        OAuth2User oAuth2User = delegate.loadUser(userRequest);             //객체에 요청한 정보들을 삽입

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    //구분자 ex:) 구글, 카카오 등등
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println("1234");
        System.out.println(registrationId);
        System.out.println(userNameAttributeName);
        System.out.println(oAuth2User);
        System.out.println(userRequest);
        System.out.println(oAuth2User.getName());
        System.out.println(oAuth2User.getAttributes());
        System.out.println("5678");

        return new DefaultOAuth2User((Collections.singleton(new SimpleGrantedAuthority("USER"))), oAuth2User.getAttributes() ,"name");
    }
}
