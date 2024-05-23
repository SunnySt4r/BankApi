package com.example.bankapi.controller;

import com.example.bankapi.dto.filter.FilterBirthdayDto;
import com.example.bankapi.dto.filter.FilterEmailDto;
import com.example.bankapi.dto.filter.FilterNameDto;
import com.example.bankapi.dto.filter.FilterPhoneDto;
import com.example.bankapi.models.UserEntity;
import com.example.bankapi.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @GetMapping("/birthday")
    public Page<UserEntity> getUsers(@RequestBody FilterBirthdayDto filterBirthdayDto) {
        PageRequest pageRequest = PageRequest.of(filterBirthdayDto.getPageNumber(), filterBirthdayDto.getPageSize());
        return filterService.getUsers(filterBirthdayDto.getBirthday(), pageRequest);
    }

    @GetMapping("/email")
    public ResponseEntity<UserEntity> getUsers(@RequestBody FilterEmailDto filterEmailDto) {
        return ResponseEntity.ok(filterService.getUsers(filterEmailDto.getEmail()));
    }

    @GetMapping("/phone")
    public ResponseEntity<UserEntity> getUsers(@RequestBody FilterPhoneDto filterPhoneDto) {
        return ResponseEntity.ok(filterService.getUsers(filterPhoneDto.getPhone()));
    }

    @GetMapping("/name")
    public Page<UserEntity> getUsers(@RequestBody FilterNameDto filterNameDto) {
        PageRequest pageRequest = PageRequest.of(filterNameDto.getPageNumber(), filterNameDto.getPageSize());
        return filterService.getUsers(filterNameDto.getName(),
                filterNameDto.getSurname(),
                filterNameDto.getMiddleName(),
                pageRequest
        );
    }
}
