package com.example.bankapi.exeption;

public class LimitException extends RuntimeException{
    public LimitException(String s){
        super(s);
    }
}
