package com.example.bankapi.controller;

import com.example.bankapi.dto.EmailAddDto;
import com.example.bankapi.dto.UiSuccessContainer;
import com.example.bankapi.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/add")
    public ResponseEntity<EmailAddDto> addEmail(@RequestBody String address){
        return ResponseEntity.ok(emailService.addEmail(address));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<UiSuccessContainer> deleteEmail(@RequestBody String address){
        return ResponseEntity.ok(emailService.deleteEmail(address));
    }

}
