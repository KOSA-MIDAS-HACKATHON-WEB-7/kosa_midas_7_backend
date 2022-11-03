package com.backend.kosa_midas_7_backend.entity.emailauthcode;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailAuthCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String code;

    private LocalDateTime expirationDate;

    private Boolean expired;

    public void setTokenToUsed() {
        this.expired = true;
    }

}
