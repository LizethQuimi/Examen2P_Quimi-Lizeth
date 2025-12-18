package com.banquito.sucursal.dto;

import com.banquito.sucursal.model.BranchState;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BranchResponseDto {
    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private BranchState state;
    private String creationDate;
    private String lastModifiedDate;
    private List<HolidayResponseDto> branchHolidays;
}
