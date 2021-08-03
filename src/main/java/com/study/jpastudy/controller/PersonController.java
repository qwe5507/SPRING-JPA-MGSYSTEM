package com.study.jpastudy.controller;

import com.study.jpastudy.domain.Person;
import com.study.jpastudy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @GetMapping
    @RequestMapping("{id}")
    public Person getPerson(@PathVariable Long id ){
        return personService.getPerson(id);
    }
}
