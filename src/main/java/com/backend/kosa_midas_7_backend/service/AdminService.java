package com.backend.kosa_midas_7_backend.service;

import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void updatePasswordAdmin(UpdatePassword updatePassword) {
        User user = userRepository.findByAccountId(updatePassword.getAccountId()).orElseThrow(RuntimeException::new);
        userRepository.save(user.changePassword(passwordEncoder.encode(updatePassword.getNewPassword())));
    }
}
