package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Person;
import com.study.jpastudy.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setAge(10);
        person.setName("martin");
        person.setBloodType("A");

        personRepository.save(person);

        List<Person> personList = personRepository.findAll();

        assertThat(personList.size()).isEqualTo(1);
        assertThat(personList.get(0).getName()).isEqualTo("martin");
        assertThat(personList.get(0).getAge()).isEqualTo(10);
        assertThat(personList.get(0).getBloodType()).isEqualTo("A");

        System.out.println(personRepository.findAll());
    }
    @Test
    void constructorTest(){
        Person person = new Person("이진현",28,"A");
    }
    @Test
    void hashCodeAndEquals(){
        Person person1 = new Person("이진강",28,"A");
        Person person2 = new Person("이진강",28,"A");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        Map<Person,Integer> map = new HashMap<>();
        map.put(person1,person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }
    @Test
    void findByBloodType(){
            givenPerson("martin", 10, "A");
            givenPerson("david", 9, "B");
            givenPerson("dennis", 8, "O");
            givenPerson("sophia", 7, "AB");
            givenPerson("benny", 6, "A");
            givenPerson("john", 6, "A");

            List<Person> result = personRepository.findByBloodType("A");

            result.forEach(System.out::println);
    }
    private void givenPerson(String name, int age, String bloodType){
        personRepository.save( new Person(name,age,bloodType));
    }
    private void givenPerson(String name, int age, String bloodType,LocalDate birthday){
        Person person = new Person(name,age,bloodType);
        person.setBirthday(new Birthday(birthday));
        personRepository.save(person);
    }
    @Test
    void findByBirthdayBetween(){
        givenPerson("martin", 10, "A",LocalDate.of(1991,8,15));
        givenPerson("david", 9, "B",LocalDate.of(1992,7,10));
        givenPerson("dennis", 8, "O",LocalDate.of(1993,1,5));
        givenPerson("sophia", 7, "AB",LocalDate.of(1994,6,30));
        givenPerson("benny", 6, "A",LocalDate.of(1995,8,30));

        List<Person> result = personRepository.findByMonthOfBirthday(8);

        result.forEach(System.out::println);
    }
}