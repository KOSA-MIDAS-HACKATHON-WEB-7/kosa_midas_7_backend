package com.backend.kosa_midas_7_backend.entity.refresh;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Refresh {
    @Id
    private String userId;

    @Column(nullable = false)
    private String token;

    @Builder
    public Refresh(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
