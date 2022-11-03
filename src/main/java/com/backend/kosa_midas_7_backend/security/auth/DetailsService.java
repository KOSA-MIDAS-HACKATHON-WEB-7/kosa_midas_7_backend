package com.backend.kosa_midas_7_backend.security.auth;

import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public Details loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(username).orElseThrow(RuntimeException::new);
        return new Details(user);
    }
}
