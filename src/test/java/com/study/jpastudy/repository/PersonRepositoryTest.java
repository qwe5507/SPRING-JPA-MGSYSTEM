package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Person;
import com.study.jpastudy.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setAge(10);
        person.setNamesa("john");
        person.setBloodType("A");

        personRepository.save(person);

        List<Person> people = personRepository.findByNamesa("martin");

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getNamesa()).isEqualTo("martin");
        assertThat(people.get(0).getAge()).isEqualTo(10);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");

        System.out.println(personRepository.findAll());
    }
    @Test
    void constructorTest(){
        Person person = new Person("이진현",28,"A");
    }
//    @Test
//    void hashCodeAndEquals(){
//        Person person1 = new Person("이진강",28,"A");
//        Person person2 = new Person("이진강",28,"A");
//
//        System.out.println(person1.equals(person2));
//        System.out.println(person1.hashCode());
//        System.out.println(person2.hashCode());
//        Map<Person,Integer> map = new HashMap<>();
//        map.put(person1,person1.getAge());
//
//        System.out.println(map);
//        System.out.println(map.get(person2));
//    }
    @Test
    void findByBloodType(){
        List<Person> result = personRepository.findByBloodType("A");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getNamesa()).isEqualTo("martin");
        assertThat(result.get(1).getNamesa()).isEqualTo("benny");
    }
    @Test
    void findByBirthdayBetween(){

        List<Person> result = personRepository.findByMonthOfBirthday(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getNamesa()).isEqualTo("martin");
        assertThat(result.get(1).getNamesa()).isEqualTo("sophia");

    }
}