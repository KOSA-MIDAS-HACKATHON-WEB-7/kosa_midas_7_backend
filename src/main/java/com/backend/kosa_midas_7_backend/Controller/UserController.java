package com.backend.kosa_midas_7_backend.Controller;

import com.backend.kosa_midas_7_backend.entity.dto.CheckEmailAuthCodeDto;
import com.backend.kosa_midas_7_backend.entity.dto.EmailAuthDto;
import com.backend.kosa_midas_7_backend.entity.dto.FindPasswordDto;
import com.backend.kosa_midas_7_backend.service.mail.MailService;
import com.backend.kosa_midas_7_backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final MailService mailService;

    // GET

    // POST
    @PostMapping("/find-id/email-auth") // 아이디 찾기 인증코드
    public ResponseEntity<HttpStatus> findId(@RequestBody EmailAuthDto emailAuthDto) throws Exception {
        return mailService.sendAuthMail(emailAuthDto);
    }

    @PostMapping("/find-password/email-auth") // 비밀번호 찾기(사실상 변경) 인증코드
    public ResponseEntity<HttpStatus> findPassword(@RequestBody FindPasswordDto findPasswordDto) throws Exception {
        return userService.findPassword(findPasswordDto);
    }

    @PostMapping("/check-auth-code")
    public ResponseEntity<Boolean> checkEmailAuthCode(@RequestBody CheckEmailAuthCodeDto checkEmailAuthCodeDto) {
        return userService.checkEmailAuthCode(checkEmailAuthCodeDto);
    }

    // PUT

    // DELETE

}
