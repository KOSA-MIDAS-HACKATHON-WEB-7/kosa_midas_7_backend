package com.backend.kosa_midas_7_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String accountId;

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
