package com.study.jpastudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    @GetMapping(value = "/api/helloworld")
    public String helloWorld(){
        return "Hello World";
    }
}
