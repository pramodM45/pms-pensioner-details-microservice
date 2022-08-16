package com.example.pensionerdetailsmicroservice.controller;


import com.example.pensionerdetailsmicroservice.exceptions.AadhaarNotFoundException;
import com.example.pensionerdetailsmicroservice.exceptions.JWTException;
import com.example.pensionerdetailsmicroservice.model.JWTValidateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class PensionerDetailsControllerAdvice {


    @ExceptionHandler(value = JWTException.class)
    public ResponseEntity<JWTValidateResponse> JWTExceptionHandler(JWTException e){
        JWTValidateResponse response = JWTValidateResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AadhaarNotFoundException.class)
    public ResponseEntity<JWTValidateResponse> JWTExceptionHandler(AadhaarNotFoundException e){
        JWTValidateResponse response = JWTValidateResponse.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}

