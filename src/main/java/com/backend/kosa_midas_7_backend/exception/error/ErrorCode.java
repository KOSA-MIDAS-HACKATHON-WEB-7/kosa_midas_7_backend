package com.backend.kosa_midas_7_backend.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    WORKHOMENOTFOUND(404, "WorkHome Column이 존재하지 않습니다."),
    PASSWORDCONFLICT(409, "password가 충돌했습니다."),
    TokenUnauthorized(401, "token 권한이 없습니다."),
    ACCEPTUNAUTHORIZED(401, "관리자에 의해 승인되지 않았습니다."),
    IDCONFLICT(409, "id가 충돌했습니다."),
    USERNOTFOUND(404, "존재하지 않는 사용자 입니다."),
    UNATHORIZED(401, "유효한 토큰이 없습니다.");


    private final int status;

    private final String message;
}
