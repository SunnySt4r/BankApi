package com.example.bankapi.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterNameDto {
    private Integer pageNumber;
    private Integer pageSize;
    private String name;
    private String surname;
    private String middleName;
}
