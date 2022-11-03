package com.backend.kosa_midas_7_backend.dto2.request.admin;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdatePassword {

    @NotBlank
    private String accountId;

    @NotBlank
    private String Password;
}
