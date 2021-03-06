package com.study.jpastudy.exception.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private int code;
    private String message;

    public static ErrorResponse of(HttpStatus httpStatus,String message){
        return new ErrorResponse(httpStatus.value(),message);
    }
    public static ErrorResponse of(HttpStatus httpStatus, FieldError fieldError){
        if(fieldError == null){
            return new ErrorResponse(httpStatus.value(),"invaild params");
        }else{
            return new ErrorResponse(httpStatus.value(),fieldError.getDefaultMessage());
        }
    }
}
