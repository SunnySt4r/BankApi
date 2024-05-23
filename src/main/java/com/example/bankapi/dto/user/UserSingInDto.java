package com.example.bankapi.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserSingInDto {
    private String login;
    private String password;
}
