package com.backend.kosa_midas_7_backend.exception;

import com.backend.kosa_midas_7_backend.exception.error.ErrorCode;
import com.backend.kosa_midas_7_backend.exception.error.MidasException;

public class PasswordConflict extends MidasException {
    public static final MidasException EXCEPTION =
            new PasswordConflict();
    private PasswordConflict() {
        super(ErrorCode.PASSWORDCONFLICT);
    }
}
