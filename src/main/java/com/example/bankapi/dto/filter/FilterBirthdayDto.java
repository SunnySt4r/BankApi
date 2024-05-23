package com.example.bankapi.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterBirthdayDto {
    private Integer pageNumber;
    private Integer pageSize;
    private Date birthday;
}
