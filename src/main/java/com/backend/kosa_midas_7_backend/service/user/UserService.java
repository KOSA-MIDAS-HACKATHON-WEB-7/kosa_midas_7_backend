package com.backend.kosa_midas_7_backend.service.user;

import com.backend.kosa_midas_7_backend.entity.dto.CheckEmailAuthCodeDto;
import com.backend.kosa_midas_7_backend.entity.dto.FindPasswordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // GET

    // POST
    ResponseEntity<HttpStatus> findPassword(FindPasswordDto findPasswordDto) throws Exception;

    ResponseEntity<Boolean> checkEmailAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto);

    // PUT

    // DELETE

    // ELSE

}
