package com.backend.kosa_midas_7_backend.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MidasException extends RuntimeException{
    private final ErrorCode errorCode;
}
