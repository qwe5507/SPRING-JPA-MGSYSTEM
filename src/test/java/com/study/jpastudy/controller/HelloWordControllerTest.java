package com.study.jpastudy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

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
    void mockMvcTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloWordController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/helloworld")
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

}