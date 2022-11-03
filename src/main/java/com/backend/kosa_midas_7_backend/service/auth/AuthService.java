package com.backend.kosa_midas_7_backend.service.auth;

import com.backend.kosa_midas_7_backend.dto.request.ChangePasswordDto;
import com.backend.kosa_midas_7_backend.dto.request.LoginDto;
import com.backend.kosa_midas_7_backend.dto.request.UserDto;
import com.backend.kosa_midas_7_backend.dto.response.TokenResponse;
import com.backend.kosa_midas_7_backend.entity.refresh.repository.RefreshRepository;
import com.backend.kosa_midas_7_backend.entity.user.Role;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.exception.AcceptUnauthorized;
import com.backend.kosa_midas_7_backend.exception.IdConflict;
import com.backend.kosa_midas_7_backend.exception.PasswordConflict;
import com.backend.kosa_midas_7_backend.exception.UserNotFound;
import com.backend.kosa_midas_7_backend.security.auth.Details;
import com.backend.kosa_midas_7_backend.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
            throw UserNotFound.EXCEPTION;
        });

        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            throw PasswordConflict.EXCEPTION;


        if(!user.getAccept()) throw AcceptUnauthorized.EXCEPTION;


        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(loginDto.getAccountId()))
                .refreshToken(jwtProvider.generateRefreshToken(loginDto.getAccountId()))
                .build();
    }

    public void signUp(UserDto userDto) {
        if(userRepository.findByAccountId(userDto.getAccountId()).isPresent()) {
            throw IdConflict.EXCEPTION;
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
                .role(tmp)
                .date(0)
                .coreTimeStart("08시")
                .coreTimeEnd("15시")
                .build());
    }
    public void logout() {
        Details tmp =  (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accountId = tmp.getUser().getAccountId();
        refreshRepository.delete(refreshRepository.findById(accountId).orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                }
        ));
    }

    public TokenResponse reissue(String accessToken) {
        Claims claims = jwtProvider.parseClaims(accessToken);
        User user = userRepository.findByAccountId(claims.getSubject())
                .orElseThrow(() -> {
                    throw UserNotFound.EXCEPTION;
                });
        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getAccountId()))
                .build();
    }

    public void updatePassword(LoginDto loginDto) {
        User user = userRepository.findByAccountId(loginDto.getAccountId()).orElseThrow(()-> {
            throw UserNotFound.EXCEPTION;
        });
        userRepository.save(user.changePassword(passwordEncoder.encode(loginDto.getPassword())));
    }

    public void updatePassword(ChangePasswordDto changePasswordDto) {
        User user = userRepository.findByAccountId((changePasswordDto.getAccountId())).orElseThrow(() -> {
            throw UserNotFound.EXCEPTION;
        });
        if(!passwordEncoder.matches(changePasswordDto.getBeforePassword(), user.getPassword())) {
            throw PasswordConflict.EXCEPTION;
        }
        if(!changePasswordDto.getAfterPassword().equals(changePasswordDto.getAfterPasswordCheck())) {
            throw PasswordConflict.EXCEPTION;
        }
        userRepository.save(user.changePassword(passwordEncoder.encode(changePasswordDto.getAfterPassword())));
    }

    public void accept(String accountId) {
        User user = userRepository.findByAccountId(accountId).orElseThrow(() ->{
            throw UserNotFound.EXCEPTION;
        });
        userRepository.save(user.changeAccept(true));
    }
}
