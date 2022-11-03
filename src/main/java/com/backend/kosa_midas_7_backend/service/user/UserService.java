package com.backend.kosa_midas_7_backend.service.user;

import com.backend.kosa_midas_7_backend.entity.dto.user.*;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // GET
    ResponseEntity<User> findUserById(Long id);

    ResponseEntity<List<User>> findAllUser();

    // POST
    ResponseEntity<HttpStatus> findPassword(FindPasswordDto findPasswordDto) throws Exception;

    ResponseEntity<String> findIdCheckAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto);

    ResponseEntity<Boolean> findPasswordCheckAuthCode(FindPasswordCheck findPasswordCheck);

    ResponseEntity<WorkHome> workHomeApplication(WorkHomeApplicationDto workHomeApplicationDto);

    // PUT
    ResponseEntity<User> changeCoreTime(ChangeCoreTimeDto changeCoreTimeDto);

    // DELETE

    // ELSE
    Boolean checkEmailAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto);

}
