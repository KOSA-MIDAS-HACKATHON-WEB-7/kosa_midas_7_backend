package com.backend.kosa_midas_7_backend.security.auth;

import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.exception.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public Details loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(username).orElseThrow(() -> {
            throw UserNotFound.EXCEPTION;
        });
        return new Details(user);
    }
}
