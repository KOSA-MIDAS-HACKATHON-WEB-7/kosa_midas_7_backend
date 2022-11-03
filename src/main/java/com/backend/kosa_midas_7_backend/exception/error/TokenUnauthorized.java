package com.backend.kosa_midas_7_backend.exception.error;

public class TokenUnauthorized extends MidasException{
    public static final MidasException EXCEPTION =
            new TokenUnauthorized();
    private TokenUnauthorized() {
        super(ErrorCode.TokenUnauthorized);
    }
}
