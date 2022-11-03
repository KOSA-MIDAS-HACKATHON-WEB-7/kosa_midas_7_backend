package com.backend.kosa_midas_7_backend.controller;

import com.backend.kosa_midas_7_backend.dto2.request.ChangePasswordDto;
import com.backend.kosa_midas_7_backend.dto2.request.LoginDto;
import com.backend.kosa_midas_7_backend.dto2.request.UserDto;
import com.backend.kosa_midas_7_backend.dto2.response.TokenResponse;
import com.backend.kosa_midas_7_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDto userDto) {
        authService.signUp(userDto);
    }

    @DeleteMapping("/logout")
    public void logout(@RequestParam String id) {
        authService.logout(id);
    }

    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestParam String refreshToken) {
        return authService.reissue(refreshToken);
    }

    @PutMapping("/update-password")
    public void updatePassword(@RequestBody LoginDto loginDto) {
        authService.updatePassword(loginDto);
    }

    @PutMapping("/update-password-mypage")
    public void updatePasswordMyPage(@RequestBody ChangePasswordDto changePasswordDto) {
        authService.updatePassword(changePasswordDto);
    }
}
