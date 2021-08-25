package com.study.jpastudy.controller;

import com.study.jpastudy.domain.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class GroupControllerTest {
    @Autowired
    GroupController groupController;
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }
    @Test
    public void getAll() throws Exception{
//        List<Group> groupList = groupController.getAll();
//        assertThat(groupList.size()).isEqualTo(1);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/group"))
                .andExpect(status().isOk())  //isOK 는 200응답의미
                .andExpect(jsonPath("$.size").value(0));
    }

    @Test
    public void GetGroupById(){
        Group group = groupController.getGroup(1L);
        assertThat(group.getDescription()).isEqualTo("첫번째 그룹");
    }
//    @Test
//    public void postGroup

}
