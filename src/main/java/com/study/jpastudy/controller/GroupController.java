package com.study.jpastudy.controller;

import com.study.jpastudy.domain.Group;
import com.study.jpastudy.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/group")
@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping
    public List<Group> groupList(){
        return groupService.getAll();
    }
}
