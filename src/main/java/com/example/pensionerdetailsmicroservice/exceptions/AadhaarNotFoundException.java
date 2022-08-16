package com.example.pensionerdetailsmicroservice.exceptions;

public class AadhaarNotFoundException extends RuntimeException{
    public AadhaarNotFoundException(String message) {
        super(message);
    }
}
