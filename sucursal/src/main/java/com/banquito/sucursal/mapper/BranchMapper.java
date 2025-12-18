package com.banquito.sucursal.mapper;

import com.banquito.sucursal.dto.BranchResponseDto;
import com.banquito.sucursal.dto.HolidayResponseDto;
import com.banquito.sucursal.model.Branch;
import com.banquito.sucursal.model.BranchHoliday;

import java.util.ArrayList;
import java.util.List;

public class BranchMapper {

    public static BranchResponseDto toDto(Branch entity) {
        BranchResponseDto dto = new BranchResponseDto();
        dto.setId(entity.getId());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setState(entity.getState());
        dto.setCreationDate(entity.getCreationDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        List<HolidayResponseDto> holidayDtos = new ArrayList<>();
        if (entity.getBranchHolidays() != null) {
            for (BranchHoliday h : entity.getBranchHolidays()) {
                HolidayResponseDto hd = new HolidayResponseDto();
                hd.setDate(h.getDate());
                hd.setName(h.getName());
                holidayDtos.add(hd);
            }
        }
        dto.setBranchHolidays(holidayDtos);
        return dto;
    }
}
