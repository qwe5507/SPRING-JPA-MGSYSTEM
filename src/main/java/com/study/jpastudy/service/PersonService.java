package com.study.jpastudy.service;

import com.study.jpastudy.controller.dto.PersonDto;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPerson(Long id ){
        Person person = personRepository.findById(id).orElse(null);

        return person;
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> people = personRepository.findAll();
//
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByNamesa(name);
    }
    @Transactional
    public void put(PersonDto personDto){
        Person person = new Person();
        person.set(personDto);
        person.setNamesa(personDto.getNamesa());
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto){
        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지않습니다."));

        if(!person.getNamesa().equals(personDto.getNamesa())){
                throw new RuntimeException("이름이 다릅니다.");
        }
        person.set(personDto);

        personRepository.save(person);
    }
    @Transactional
    public void modify(Long id, String name){
        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지않습니다."));

        person.setNamesa(name);

        personRepository.save(person);
    }
    @Transactional
    public void delete(Long id) {
//        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지않습니다."));
//        personRepository.delete(person);

//        personRepository.deleteById(id);

        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지않습니다."));

        person.setDeleted(true);

        personRepository.save(person);
    }
}
