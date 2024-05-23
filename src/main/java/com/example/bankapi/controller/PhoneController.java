package com.example.bankapi.controller;

import com.example.bankapi.dto.PhoneAddDto;
import com.example.bankapi.dto.UiSuccessContainer;
import com.example.bankapi.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phone")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    @PostMapping("/add")
    public ResponseEntity<PhoneAddDto> addPhone(@RequestBody Long number){
        return ResponseEntity.ok(phoneService.addPhone(number));
    }


    @DeleteMapping("/{phone-number}")
    public ResponseEntity<UiSuccessContainer> deletePhone(@PathVariable(name = "phone-number") Long number){
        return ResponseEntity.ok(phoneService.deletePhone(number));
    }

}
