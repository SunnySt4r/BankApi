package com.example.bankapi.dto.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddNameDto {
    private String surname;
    private String name;
    private String middleName;
}
