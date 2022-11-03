package com.backend.kosa_midas_7_backend.service.mail;

import com.backend.kosa_midas_7_backend.entity.EmailAuthCode;
import com.backend.kosa_midas_7_backend.entity.dto.EmailAuthDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public interface MailService {

    // GET
    MimeMessage createAuthMessage(String name, String email) throws Exception;

    // PUT

    // DELETE

    // ELSE
    ResponseEntity<Boolean> checkAuthCode(String email, String code);

    ResponseEntity<HttpStatus> sendAuthMail(EmailAuthDto emailAuthDto) throws Exception;

    EmailAuthCode createAuthToken(String email, String code);

}
