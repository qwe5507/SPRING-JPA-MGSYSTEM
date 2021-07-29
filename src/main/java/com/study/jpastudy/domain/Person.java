package com.study.jpastudy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

}
