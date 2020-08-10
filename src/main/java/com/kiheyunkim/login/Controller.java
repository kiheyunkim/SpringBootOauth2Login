package com.kiheyunkim.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
    @GetMapping("/")
    public String getMain(){
        return "12345";
    }
    @GetMapping("/board")
    public String getBn(){
        return "1234567890";
    }

    /*@GetMapping("/login")
    public ModelAndView getLoginPage(ModelAndView model){
        model.setViewName("login");
        model.addObject("hi","Login Test");

        return model;
    }*/
}
