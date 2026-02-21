package com.example.socialLogin.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {

    @GetMapping("/user")
    String getUserData(Model model, @AuthenticationPrincipal OAuth2User principal){
        String name = principal.getAttribute("given_name");
        model.addAttribute("name",name);
        System.out.println(principal);
        return "user";
    }
}
