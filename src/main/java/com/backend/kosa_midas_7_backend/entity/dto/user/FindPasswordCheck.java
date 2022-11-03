package com.backend.kosa_midas_7_backend.entity.dto.user;

import lombok.Data;

@Data
public class FindPasswordCheck {

    private String accountId;

    private String code;

}
