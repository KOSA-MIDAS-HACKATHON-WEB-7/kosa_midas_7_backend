package com.backend.kosa_midas_7_backend.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CheckOvertimeDto {

    private LocalDate startTime;

    private LocalDate endTime;

}
