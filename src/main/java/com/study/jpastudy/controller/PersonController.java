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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void postPerson(@RequestBody PersonDto personDto){
        personService.put(personDto);
    }

    @PutMapping("{id}")
    public void modifyPerson(@PathVariable Long id,@RequestBody PersonDto personDto){
        personService.modify(id,personDto);
    }
    @ExceptionHandler(value = RenameNotPermittedException.class)
    public ResponseEntity<ErrorResponse> handleRenameNoPermittedException(RenameNotPermittedException ex){
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(PersonNotFoundException ex){
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
        log.info("서버오류 : {}",ex.getMessage(),ex);
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"알수없는 서버 오류가 발생하였습니다."),HttpStatus.INTERNAL_SERVER_ERROR);
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
