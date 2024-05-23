package com.example.bankapi.dto.user;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserSingUpDto {
    private String login;
    private String password;
    @Positive
    private Float deposit;
    private Long phone;
    private String email;
}
