package com.banquito.sucursal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchUpdatePhoneRequestDto {
    @NotBlank
    private String phoneNumber;
}
