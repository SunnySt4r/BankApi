package com.example.bankapi.exeption.handler;

import com.example.bankapi.dto.UiSuccessContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler{

    @ExceptionHandler({Exception.class})
    public ResponseEntity<UiSuccessContainer> handleUnexpectedException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new UiSuccessContainer(false, "Непредвиденная ошибка"));
    }
}