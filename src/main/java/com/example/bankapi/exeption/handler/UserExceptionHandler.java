package com.example.bankapi.exeption.handler;

import com.example.bankapi.dto.UiSuccessContainer;
import com.example.bankapi.exeption.LimitException;
import com.example.bankapi.exeption.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {

    @ExceptionHandler(LimitException.class)
    public ResponseEntity<UiSuccessContainer> handleInvalidImageException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new UiSuccessContainer(false, ex.getMessage()));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<UiSuccessContainer> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new UiSuccessContainer(false, ex.getMessage()));
    }
}
