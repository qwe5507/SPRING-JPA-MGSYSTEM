package com.study.jpastudy.controller;

import com.study.jpastudy.controller.dto.PersonDto;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.repository.PersonRepository;
import com.study.jpastudy.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired PersonService personService;
    @Autowired
    PersonRepository personRepository;

    @GetMapping("{id}")
    public Person getPerson(@PathVariable Long id ){
        return personService.getPerson(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person){
        personService.put(person);
    }
    @PutMapping("{id}")
    public void modifyPerson(@PathVariable Long id,@RequestBody PersonDto personDto){
        personService.modify(id,personDto);

    }

    @PatchMapping("{id}")
    public void modifyPerson(@PathVariable Long id,String namesa){
        personService.modify(id,namesa); //modify오버로딩 작성

    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);


//        return personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(id));

    }
}
