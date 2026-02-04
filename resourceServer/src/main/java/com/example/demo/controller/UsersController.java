package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/v1")
public class UsersController {

    @GetMapping("/check/status")
    String checkUserStatus(){
        return "User is ok";
    }
}
