package com.backend.kosa_midas_7_backend.service.user;

import com.backend.kosa_midas_7_backend.entity.dto.user.ChangeCoreTimeDto;
import com.backend.kosa_midas_7_backend.entity.dto.user.CheckEmailAuthCodeDto;
import com.backend.kosa_midas_7_backend.entity.dto.user.FindPasswordCheck;
import com.backend.kosa_midas_7_backend.entity.dto.user.FindPasswordDto;
import com.backend.kosa_midas_7_backend.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // GET

    // POST
    ResponseEntity<HttpStatus> findPassword(FindPasswordDto findPasswordDto) throws Exception;

    ResponseEntity<String> findIdCheckAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto);

    ResponseEntity<Boolean> findPasswordCheckAuthCode(FindPasswordCheck findPasswordCheck);

    // PUT
    ResponseEntity<User> changeCoreTime(ChangeCoreTimeDto changeCoreTimeDto);

    // DELETE

    // ELSE
    Boolean checkEmailAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto);

}
