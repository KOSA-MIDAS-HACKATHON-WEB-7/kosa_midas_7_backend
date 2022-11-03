package com.backend.kosa_midas_7_backend.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    WORKHOMENOTFOUND(404, "WorkHome Column이 존재하지 않습니다."),
    UNATHORIZED(401, "유효한 토큰이 없습니다.");


    private final int status;

    private final String message;
}
