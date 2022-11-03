package com.backend.kosa_midas_7_backend.dto.request.admin;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateAccountId {

    @NotBlank
    private String accountId;

    @NotBlank
    private String newAccountId;
}
