package com.study.jpastudy.service;

import com.study.jpastudy.domain.Group;
import com.study.jpastudy.domain.Person;
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
    public void modifyGroup(Long id, String description) {
        Group group = groupRepository.findById(id).get();
        group.setDescription(description);
        Group newGroup = groupRepository.save(group);

    }
    public List<Person> getPeopleInGroup(Long id) {
          return groupRepository.findById(id).get().getPersonList();
    }
}
