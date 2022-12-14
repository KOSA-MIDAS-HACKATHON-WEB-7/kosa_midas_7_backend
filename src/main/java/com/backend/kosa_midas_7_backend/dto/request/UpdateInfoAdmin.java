package com.backend.kosa_midas_7_backend.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateInfoAdmin {

    @NotBlank
    private String accountId;

    private String password;

    private String position;

    private String department;

    private String newAccountId;
}
