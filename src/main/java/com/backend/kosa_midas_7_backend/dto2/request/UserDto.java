package com.backend.kosa_midas_7_backend.dto2.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotBlank
    private String department;

    @NotBlank
    private String position;
}
