package com.backend.kosa_midas_7_backend.service.user;

import com.backend.kosa_midas_7_backend.entity.dto.CheckEmailAuthCodeDto;
import com.backend.kosa_midas_7_backend.entity.dto.EmailAuthDto;
import com.backend.kosa_midas_7_backend.entity.dto.FindPasswordDto;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MailService mailService;

    // GET

    // POST

    @Override
    public ResponseEntity<HttpStatus> findPassword(FindPasswordDto findPasswordDto) throws Exception {
        String accountId = findPasswordDto.getAccountId();
        User user = userRepository.findByAccountId(accountId).orElseThrow(RuntimeException::new);
        String username = user.getUserName();
        String email = user.getEmail();

        EmailAuthDto emailAuthDto = new EmailAuthDto(username, email);

        return mailService.sendAuthMail(emailAuthDto);
    }

    @Override
    public ResponseEntity<Boolean> checkEmailAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto) {
        String email = checkEmailAuthCodeDto.getEmail();
        String code = checkEmailAuthCodeDto.getCode();

        return mailService.checkAuthCode(email, code);
    }

    // PUT

    // DELETE

    // ELSE

}
