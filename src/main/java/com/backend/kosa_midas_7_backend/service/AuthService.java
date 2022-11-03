package com.backend.kosa_midas_7_backend.service;

import com.backend.kosa_midas_7_backend.dto2.request.ChangePasswordDto;
import com.backend.kosa_midas_7_backend.dto2.request.LoginDto;
import com.backend.kosa_midas_7_backend.dto2.request.UserDto;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.dto2.response.TokenResponse;
import com.backend.kosa_midas_7_backend.entity.refresh.repository.RefreshRepository;
import com.backend.kosa_midas_7_backend.entity.user.Role;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final RefreshRepository refreshRepository;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(LoginDto loginDto) {
        User user = userRepository.findByAccountId(loginDto.getAccountId()).orElseThrow(()-> {
            throw new RuntimeException("user does not exist");
        });

        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("password does not match");
        }

        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(loginDto.getAccountId()))
                .refreshToken(jwtProvider.generateRefreshToken(loginDto.getAccountId()))
                .build();
    }

    public void signUp(UserDto userDto) {
        if(userRepository.findByAccountId(userDto.getAccountId()).isPresent()) {
            throw new RuntimeException("id already exist");
        }
        Role tmp = null;
        if(userDto.getRole().equals("USER")) {
            tmp = Role.USER;
        } else if (userDto.getRole().equals("ADMIN")) {
            tmp = Role.ADMIN;
        }
        userRepository.save(User.builder()
                .accountId(userDto.getAccountId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .department(userDto.getDepartment())
                .position(userDto.getPosition())
<<<<<<< HEAD
                .role(tmp)
=======
                .date(0)
                .coreTimeStart("8시")
                .coreTimeEnd("3시")
>>>>>>> aa10220e93163d133f1fcdd10bcd241b50da68d3
                .build());
    }
    public void logout(String accountId) {
        refreshRepository.delete(refreshRepository.findById(accountId).orElseThrow(() -> {
                    throw new RuntimeException("user does not exist");
                }
        ));
    }

    public TokenResponse reissue(String accessToken) {
        Claims claims = jwtProvider.parseClaims(accessToken);
        User user = userRepository.findByAccountId(claims.getSubject())
                .orElseThrow(() -> {
                    throw new RuntimeException("user does not exist");
                });
        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getAccountId()))
                .build();
    }

    public void updatePassword(LoginDto loginDto) {
        User user = userRepository.findByAccountId(loginDto.getAccountId()).orElseThrow(()-> {
            throw new RuntimeException("user does not exist");
        });
        userRepository.save(user.changePassword(passwordEncoder.encode(loginDto.getPassword())));
    }

    public void updatePassword(ChangePasswordDto changePasswordDto) {
        User user = userRepository.findByAccountId((changePasswordDto.getAccountId())).orElseThrow(() -> {
            throw new RuntimeException("user does not exist");
        });
        if(!passwordEncoder.matches(changePasswordDto.getBeforePassword(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }
        if(!changePasswordDto.getAfterPassword().equals(changePasswordDto.getAfterPasswordCheck())) {
            throw new RuntimeException("check your \"check password\"");
        }
        userRepository.save(user.changePassword(passwordEncoder.encode(changePasswordDto.getAfterPassword())));
    }
}
