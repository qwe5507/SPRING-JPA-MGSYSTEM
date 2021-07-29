package com.study.jpastudy.repository;

import com.study.jpastudy.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
