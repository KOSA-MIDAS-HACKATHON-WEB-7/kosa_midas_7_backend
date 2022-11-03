package com.backend.kosa_midas_7_backend.controller;

import com.backend.kosa_midas_7_backend.dto.request.*;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import com.backend.kosa_midas_7_backend.service.mail.MailService;
import com.backend.kosa_midas_7_backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final MailService mailService;

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/user-info")
    public UserDto getUserInfo(@RequestParam String token) {
        return userService.getUserInfo(token);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUser() {
        return userService.findAllUser();
    }

    // POST
    @PostMapping("/sign-up/email-auth")
    public ResponseEntity<HttpStatus> signUpEmailAuth(@RequestBody EmailAuthDto emailAuthDto) throws Exception {
        return mailService.sendAuthMail(emailAuthDto);
    }

    @PostMapping("/find-id/email-auth") // 아이디 찾기 인증코드
    public ResponseEntity<HttpStatus> findId(@RequestBody EmailAuthDto emailAuthDto) throws Exception {
        return mailService.sendAuthMail(emailAuthDto);
    }

    @PostMapping("/find-password/email-auth") // 비밀번호 찾기(사실상 변경) 인증코드
    public ResponseEntity<HttpStatus> findPassword(@RequestBody FindPasswordDto findPasswordDto) throws Exception {
        return userService.findPassword(findPasswordDto);
    }

    @PostMapping("/sign-up/check-auth-code")
    public ResponseEntity<Boolean> signUpCheck(@RequestBody CheckEmailAuthCodeDto emailAuthCodeDto) {
        return userService.signUpCheck(emailAuthCodeDto);
    }

    @PostMapping("/find-id/check-auth-code") // 아이디 인증코드 확인 -> 맞으면 아이디 보여줌
    public ResponseEntity<String> findIdCheck(@RequestBody CheckEmailAuthCodeDto checkEmailAuthCodeDto) {
        return userService.findIdCheckAuthCode(checkEmailAuthCodeDto);
    }

    @PostMapping("/find-password/check-auth-code") // 비밀번호 인증코드 확인 -> 맞으면 true
    public ResponseEntity<Boolean> findPasswordCehck(@RequestBody FindPasswordCheck findPasswordCheck) {
        return userService.findPasswordCheckAuthCode(findPasswordCheck);
    }

    @PostMapping("/work-home/application")
    public ResponseEntity<WorkHome> workHomeApplication(@RequestBody WorkHomeApplicationDto workHomeApplicationDto) {
        return userService.workHomeApplication(workHomeApplicationDto);
    }

    // PUT
    @PutMapping("/change-core-time")
    public ResponseEntity<User> changeCoreTime(@RequestBody ChangeCoreTimeDto changeCoreTimeDto) {
        log.info("g: {}", changeCoreTimeDto.toString());
        return userService.changeCoreTime(changeCoreTimeDto);
    }

    // DELETE

}
