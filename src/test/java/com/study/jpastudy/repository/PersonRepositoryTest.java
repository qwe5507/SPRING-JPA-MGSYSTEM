package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Person;
import com.study.jpastudy.domain.dto.Birthday;
import org.junit.jupiter.api.Disabled;
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
    void findByName(){
        List<Person> people = personRepository.findByNamesa("tony");
        assertThat(people.size()).isEqualTo(1);

        Person person = people.get(0);
        assertAll(
                ()->assertThat(person.getNamesa()).isEqualTo("tony") ,
                ()->assertThat(person.getHobby()).isEqualTo("reading") ,
                ()->assertThat(person.getAddress()).isEqualTo("서울") ,
                ()->assertThat(person.getBirthday()).isEqualTo(Birthday.of(LocalDate.of(1992,7,10))) ,
                ()->assertThat(person.getJob()).isEqualTo("officer") ,
                ()->assertThat(person.getPhoneNumber()).isEqualTo("010-2222-5555"),
                ()->assertThat(person.isDeleted()).isEqualTo(false)
        );
    }
    @Test
    void findByNameIfDeleted(){
        List<Person> people = personRepository.findByNamesa("andrew");
        assertThat(people.size()).isEqualTo(0);
    }
    @Test
    void findByMonthOfBirthday(){
        List<Person> people = personRepository.findByMonthOfBirthday(8);

        assertThat(people.size()).isEqualTo(2);

        assertAll(
                ()->assertThat(people.get(0).getNamesa()).isEqualTo("martin") ,
                ()->assertThat(people.get(1).getNamesa()).isEqualTo("sophia")
        );
    }
    @Test
    void findPeopleDeleted(){
        List<Person> people = personRepository.findPeopleDeleted();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getNamesa()).isEqualTo("andrew");

    }
}