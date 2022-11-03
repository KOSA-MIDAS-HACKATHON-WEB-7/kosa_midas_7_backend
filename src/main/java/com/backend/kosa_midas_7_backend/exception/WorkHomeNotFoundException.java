package com.backend.kosa_midas_7_backend.exception;

import com.backend.kosa_midas_7_backend.exception.error.ErrorCode;
import com.backend.kosa_midas_7_backend.exception.error.MidasException;

public class WorkHomeNotFoundException extends MidasException {
    public static final MidasException EXCEPTION =
            new WorkHomeNotFoundException();
    private WorkHomeNotFoundException() {
        super(ErrorCode.WORKHOMENOTFOUND);
    }
}
