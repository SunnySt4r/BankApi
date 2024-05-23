package com.example.bankapi.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UiSuccessContainer {
    private boolean status;
    private String message;
}
