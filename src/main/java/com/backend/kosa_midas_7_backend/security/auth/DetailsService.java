package com.backend.kosa_midas_7_backend.security.auth;

import com.backend.kosa_midas_7_backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {
    @Override
    public Details loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = User.builder()
                .accountId("")
                .password("")
                .department("")
                .email("")
                .userName("")
                .position("")
                .build();
        return new Details(user);
    }
}
