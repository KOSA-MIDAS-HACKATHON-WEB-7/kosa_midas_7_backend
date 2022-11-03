package com.backend.kosa_midas_7_backend.dto.request;

import lombok.Data;

@Data
public class CheckEmailAuthCodeDto {

    private String email;

    private String code;

}
