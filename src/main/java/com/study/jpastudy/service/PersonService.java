package com.study.jpastudy.service;

import com.study.jpastudy.domain.Block;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.repository.BlockRepository;
import com.study.jpastudy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();
        List<Block> blocks = blockRepository.findAll();
        List<String> blockName = blocks.stream().map(Block::getName).collect(Collectors.toList());

        return people.stream().filter(person -> !(blockName.contains(person.getName())  )).collect(Collectors.toList());

    }
}
