package com.example.bankapi.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterEmailDto {
    private Integer pageNumber;
    private Integer pageSize;
    private String email;
}