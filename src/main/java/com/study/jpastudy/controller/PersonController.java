package com.study.jpastudy.controller;

import com.study.jpastudy.controller.dto.PersonDto;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.exception.PersonNotFoundException;
import com.study.jpastudy.exception.RenameNotPermittedException;
import com.study.jpastudy.exception.dto.ErrorResponse;
import com.study.jpastudy.repository.PersonRepository;
import com.study.jpastudy.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired PersonService personService;
    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public Page<Person> getAll(@PageableDefault Pageable pageable){
        return personService.getAll(pageable);
    }

    @GetMapping("/birthday-friends")
    public List<Person> getBirthdayFriends(){
        return personService.getBirthdayFriends();
    }

    @GetMapping("{id}")
    public Person getPerson(@PathVariable Long id ){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody  PersonDto personDto){
        personService.put(personDto);
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
