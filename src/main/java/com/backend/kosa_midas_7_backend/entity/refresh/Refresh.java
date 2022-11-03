package com.backend.kosa_midas_7_backend.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Refresh {
    @Id
    private Long useri-d;

    @Column(nullable = false)
    private String token;
}
