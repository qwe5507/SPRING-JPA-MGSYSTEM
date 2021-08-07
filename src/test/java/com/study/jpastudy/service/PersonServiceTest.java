package com.study.jpastudy.service;

import com.study.jpastudy.domain.Person;
import com.study.jpastudy.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void getPeopleByName(){
        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getNamesa()).isEqualTo("martin");
    }
    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);

        assertThat(person.getNamesa()).isEqualTo("dennis");
    }

//    @Test //테스트용도 였기때문에 삭제
//    void cascadeTest(){
//            givenPeople();
//
//            List<Person> result = personRepository.findAll();
//            result.forEach(System.out::println);
//
//            Person person = result.get(3);
//            person.getBlock().setStartDate(LocalDate.now());
//            person.getBlock().setEndDate(LocalDate.now());
//
//            personRepository.save(person);
//            personRepository.findAll().forEach(System.out::println);
//
////            personRepository.delete(person);
////            personRepository.findAll().forEach(System.out::println);
////            blockRepository.findAll().forEach(System.out::println);
//
//        person.setBlock(null);
//        personRepository.save(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);
//    }


//    private void givenBlockPerson(String name,int age,String bloodType){
//        Person blockPerson = new Person(name,age,bloodType);
//        blockPerson.setBlock(new Block(name));
//
//        personRepository.save(blockPerson);
//    }
//    private void givenPeople() {
//        givenPerson("martin",10,"A");
//        givenPerson("david",9,"B");
//        givenBlockPerson("dennis",7,"O");
//        givenBlockPerson("martin",11,"AB");
//    }
//    private void givenPerson(String name, int age, String bloodType) {
//        personRepository.save(new Person(name,age,bloodType));
//    }

}