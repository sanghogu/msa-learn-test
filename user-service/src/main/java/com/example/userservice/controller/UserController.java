package com.example.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/users")
@Controller
public class UserController {


    @GetMapping("/health")
    @ResponseBody
    public String index(){

        return "OK";
    }


}
