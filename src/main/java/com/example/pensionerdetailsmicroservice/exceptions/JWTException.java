package com.example.pensionerdetailsmicroservice.exceptions;

public class JWTException extends RuntimeException {
    public JWTException(String message) {
        super(message);
    }

}
