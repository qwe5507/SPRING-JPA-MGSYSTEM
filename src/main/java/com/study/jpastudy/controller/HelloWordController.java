package com.study.jpastudy.controller;

import com.study.jpastudy.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWordController {
    @GetMapping(value = "/api/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(value = "/api/helloException")
    public String helloException(){
        throw new RuntimeException("Hello RuntimeException");
    }

}
