package com.study.jpastudy.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonNotFoundException extends RuntimeException{
    private static final String MESSAGE = "person 못찾음";
    public PersonNotFoundException(){
        super(MESSAGE);
        log.info(MESSAGE);
    }
}
