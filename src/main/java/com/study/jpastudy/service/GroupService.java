package com.study.jpastudy.service;

import com.study.jpastudy.domain.Group;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.repository.GroupRepository;
import com.study.jpastudy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    PersonRepository personRepository;

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

    public void putPersonInGroup(Long id, Long personId) {
        Optional<Group> group = groupRepository.findById(id);
        group.map(newGroup -> {
            newGroup.getPersonList().add(personRepository.findById(id).get());

            return groupRepository.save(newGroup);
        })
                .orElseGet(() -> null);
    }
}
