package com.banquito.sucursal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HolidayResponseDto {
    private LocalDate date;
    private String name;
}
