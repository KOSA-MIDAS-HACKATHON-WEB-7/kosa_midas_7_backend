package com.backend.kosa_midas_7_backend.entity.emailauthcode.repository;

import com.backend.kosa_midas_7_backend.entity.emailauthcode.EmailAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EmailAuthCodeRepository extends JpaRepository<EmailAuthCode, Long> {

    EmailAuthCode findByEmail(String email);

    EmailAuthCode findByEmailAndExpirationDateAfterAndExpired(String email, LocalDateTime now, boolean expired);

}
