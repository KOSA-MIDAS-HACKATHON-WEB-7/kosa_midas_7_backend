package com.backend.kosa_midas_7_backend.entity.dto;

import lombok.Data;

@Data
public class CheckEmailAuthCodeDto {

    private String email;

    private String code;

}
