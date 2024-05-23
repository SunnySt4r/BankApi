package com.example.bankapi.controller;

import com.example.bankapi.dto.AccountResponseDto;
import com.example.bankapi.dto.SendMoneyDto;
import com.example.bankapi.dto.TransactionDto;
import com.example.bankapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<AccountResponseDto> getBalance(){
        return ResponseEntity.ok(accountService.getBalance());
    }

    @PostMapping("/send")
    public ResponseEntity<TransactionDto> sendMoney(@RequestBody @Valid SendMoneyDto sendMoneyDto){
        return ResponseEntity.ok(accountService.sendMoney(sendMoneyDto.getLogin(), sendMoneyDto.getMoney()));
    }

}
