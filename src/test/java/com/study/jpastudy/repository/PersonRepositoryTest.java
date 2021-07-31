package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setAge(28);
        person.setName("이진소프트");
        person.setBloodType("A");

        personRepository.save(person);

        List<Person> personList = personRepository.findAll();

        assertThat(personList.size()).isEqualTo(1);
        assertThat(personList.get(0).getName()).isEqualTo("이진소프트");
        assertThat(personList.get(0).getAge()).isEqualTo(28);
        assertThat(personList.get(0).getBloodType()).isEqualTo("A");

        System.out.println(personRepository.findAll());
    }
    @Test
    void constructorTest(){
        Person person = new Person("이진현",28);
    }
    @Test
    void hashCodeAndEquals(){
        Person person1 = new Person("이진강",28);
        Person person2 = new Person("이진소프트",29);

        System.out.println(person1.equals(person2));
    }
}