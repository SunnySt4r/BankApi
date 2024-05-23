package com.example.bankapi.controller;


import com.example.bankapi.dto.user.UserAddNameDto;
import com.example.bankapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/set/name")
    public ResponseEntity<String> setName(@RequestBody @Valid UserAddNameDto userAddNameDto){
        return ResponseEntity.ok(userService.setName(
                userAddNameDto.getSurname(),
                userAddNameDto.getName(),
                userAddNameDto.getMiddleName()));
    }

    @PostMapping("/set/birthday")
    public ResponseEntity<Date> setBirthday(@RequestBody Date birthday){
        return ResponseEntity.ok(userService.setBirthday(birthday));
    }

}
