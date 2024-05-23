package com.example.bankapi.controller;

import com.example.bankapi.service.AuthService;
import com.example.bankapi.dto.JwtAuthenticationResponse;
import com.example.bankapi.dto.user.UserSingInDto;
import com.example.bankapi.dto.user.UserSingUpDto;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Hidden
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reg")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid UserSingUpDto request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody UserSingInDto request) {
        return authService.signIn(request);
    }
}