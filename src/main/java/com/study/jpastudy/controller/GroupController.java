package com.study.jpastudy.controller;

import com.study.jpastudy.controller.dto.GroupDto;
import com.study.jpastudy.domain.Group;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/group")
@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping
    public List<Group> getAll(){
        return groupService.getAll();
    }
    @GetMapping("/{id}")
    public Group getGroup(@PathVariable Long id){
        return groupService.getGroupById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setDescription(groupDto.getDescription());
        groupService.postGroup(group);

    }
    @PatchMapping("/{id}")
    public void modifyGroup(@PathVariable(name = "id") Long id,String description){
        groupService.modifyGroup(id,description);
    }
    @GetMapping("/{id}/people")
    public List<Person> getPeopleInGroup(@PathVariable Long id){
        return groupService.getPeopleInGroup(id);
    }

}
