package com.kiheyunkim.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity // 1
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;

    public SecurityConfig(CustomOauth2UserService customOauth2UserService){
        this.customOauth2UserService = customOauth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf();

        http
                    .authorizeRequests()
                    .antMatchers("/", "/service", "/resources/**","/login","/login/oauth2/code/google").permitAll()
                    .antMatchers("/board").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOauth2UserService);
                /*.and()
                    .formLogin() // 8
                    .loginPage("/login") // 로그인 페이지 링크
                    .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                    .logout() // 9
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true);// 세션 날리기*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
