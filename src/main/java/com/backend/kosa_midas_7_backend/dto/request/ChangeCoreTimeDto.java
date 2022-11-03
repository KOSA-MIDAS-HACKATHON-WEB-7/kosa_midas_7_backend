package com.backend.kosa_midas_7_backend.dto.request;

import lombok.Data;

@Data
public class ChangeCoreTimeDto {

    private Long userId;

    private String coreTimeStart;

    private String coreTimeEnd;

}
