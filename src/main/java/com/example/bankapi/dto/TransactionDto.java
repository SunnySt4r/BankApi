package com.example.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {
    private String fromUser;
    private Float number;
    private String toUser;
}
