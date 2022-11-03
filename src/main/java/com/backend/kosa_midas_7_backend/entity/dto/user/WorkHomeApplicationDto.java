package com.backend.kosa_midas_7_backend.entity.dto.user;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
public class WorkHomeApplicationDto {

    private Long id;

    private Long userId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

}
