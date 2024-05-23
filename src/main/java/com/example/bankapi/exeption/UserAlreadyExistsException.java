package com.example.bankapi.exeption;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
