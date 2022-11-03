package com.backend.kosa_midas_7_backend.dto2.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

    @Builder
    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
