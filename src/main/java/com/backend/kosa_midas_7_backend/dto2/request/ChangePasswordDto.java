package com.backend.kosa_midas_7_backend.dto2.request;

import lombok.Getter;

@Getter
public class ChangePasswordDto {
    private String accountId;

    private String beforePassword;

    private String afterPassword;

    private String afterPasswordCheck;
}
