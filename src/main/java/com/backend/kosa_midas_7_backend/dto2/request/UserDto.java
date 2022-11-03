package com.backend.kosa_midas_7_backend.dto2.request;

import com.backend.kosa_midas_7_backend.entity.user.Role;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String accountId;

    @NotBlank
    @Size(min = 4)
    private String password;

    @NotBlank
    private String userName;

    @NotBlank
    private String department;

    @NotBlank
    private String position;

    @NotBlank
    private String role;
}
