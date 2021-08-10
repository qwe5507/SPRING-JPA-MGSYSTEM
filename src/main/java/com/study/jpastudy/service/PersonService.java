package com.study.jpastudy.service;

import com.study.jpastudy.controller.dto.PersonDto;
import com.study.jpastudy.domain.Person;
import com.study.jpastudy.exception.PersonNotFoundException;
import com.study.jpastudy.exception.RenameNotPermittedException;
import com.study.jpastudy.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Page<Person> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> people = personRepository.findAll();
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByNamesa(name);
    }
    @Transactional(readOnly = true)
    public Person getPerson(Long id ){
        Person person = personRepository.findById(id).orElse(null);

        return person;
    }


    @Transactional
    public void put(PersonDto personDto){
        Person person = new Person();
        person.set(personDto);
        person.setNamesa(personDto.getNamesa());
        personRepository.save(person);
    }

//    @Transactional
//    public void modify(Long id, PersonDto personDto){
//        Person person = personRepository.findById(id).orElseThrow(()->new PersonNotFoundException());
//
//        if(!person.getNamesa().equals(personDto.getNamesa())){
//                throw new RenameNotPermittedException();
//        }
//        person.set(personDto);
//
//        System.out.println(person);
//        personRepository.save(person);
//    }
    @Transactional
    public void modify(Long id, String name){
        Person person = personRepository.findById(id).orElseThrow(()->new PersonNotFoundException( ));

        person.setNamesa(name);

        personRepository.save(person);
    }
    @Transactional
    public void delete(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()->new PersonNotFoundException());

        person.setDeleted(true);

        personRepository.save(person);
    }


    public List<Person> getBirthdayFriends() {
        LocalDate today = LocalDate.now();
                int month = today.getMonthValue();
                int day = today.getDayOfMonth();
        List<Person> personList =  personRepository.getBirthdayFriends(month,day);
        return personList;
    }
}
