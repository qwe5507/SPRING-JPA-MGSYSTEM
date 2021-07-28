package com.study.jpastudy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWordControllerTest  {
    @Autowired
    HelloWordController helloWordController;

    private MockMvc mockMvc;

    @Test
    void helloWorld(){
//        System.out.println("test");
        System.out.println(helloWordController.helloWorld());

        assertThat(helloWordController.helloWorld()).isEqualTo("Hello World");
    }
    @Test
    void mockMvcTest(){
//        mockMvc = MockMvcBuilders.standaloneSetup(HelloWordController).build();
    }

}