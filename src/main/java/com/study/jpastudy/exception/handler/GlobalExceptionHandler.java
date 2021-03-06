package com.study.jpastudy.exception.handler;

import com.study.jpastudy.exception.PersonNotFoundException;
import com.study.jpastudy.exception.RenameNotPermittedException;
import com.study.jpastudy.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RenameNotPermittedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRenameNoPermittedException(RenameNotPermittedException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PersonNotFoundException.class)
    public ErrorResponse handlePersonNotFoundException(PersonNotFoundException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgmentNotVaildException(MethodArgumentNotValidException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getBindingResult().getFieldError());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex){
        log.info("서버오류 : {}",ex.getMessage(),ex);
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"알수없는 서버 오류가 발생하였습니다.");
    }
}
