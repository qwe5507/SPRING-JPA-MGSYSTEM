package com.study.jpastudy.service;

import com.study.jpastudy.domain.Group;
import com.study.jpastudy.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public List<Group> getAll(){
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).get();
    }

    public Group postGroup(Group group) {
        return groupRepository.save(group);

    }
}
