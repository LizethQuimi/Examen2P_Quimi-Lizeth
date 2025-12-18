package com.banquito.sucursal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchCreateRequestDto {
    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;
}
