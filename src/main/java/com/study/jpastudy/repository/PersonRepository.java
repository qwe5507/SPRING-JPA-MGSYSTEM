package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface
PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByNamesa(String name);
//    List<Person> findByBlockIsNull();

    @Query(value = "select  person from Person person where person.birthday.monthOfBirthday = :monthOfBirthdayss")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthdayss") int monthOfBirthday);

//    @Query(value = "select  * from person where month_of_birthday = :monthOfBirthdayss and day_of_birthday = :dayOfBirthdayss",nativeQuery = true)
//    List<Person> findByMonthOfBirthday(@Param("monthOfBirthdayss") int monthOfBirthday,@Param("dayOfBirthdayss") int dayOfBirthday);

    @Query(value = "select * from Person person where person.deleted = true",nativeQuery = true)
    List<Person> findPeopleDeleted();

    @Query(value = "select person from Person person where (person.birthday.monthOfBirthday = :todayMonth and person.birthday.dayOfBirthday = :todayDay)" +
            "   or (person.birthday.monthOfBirthday = :yesterdayMonth and person.birthday.dayOfBirthday = :yesterdayDay)")
    List<Person> getBirthdayFriends(@Param("todayMonth") int todayMonth,@Param("todayDay") int todayDay,
                @Param("yesterdayMonth") int yesterdayMonth,@Param("yesterdayDay") int yesterdayDay);
}
