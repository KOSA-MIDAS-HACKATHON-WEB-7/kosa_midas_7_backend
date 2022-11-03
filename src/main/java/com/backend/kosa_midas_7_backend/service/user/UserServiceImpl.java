package com.backend.kosa_midas_7_backend.service.user;

import com.backend.kosa_midas_7_backend.entity.dto.user.CheckEmailAuthCodeDto;
import com.backend.kosa_midas_7_backend.entity.dto.user.EmailAuthDto;
import com.backend.kosa_midas_7_backend.entity.dto.user.FindPasswordCheck;
import com.backend.kosa_midas_7_backend.entity.dto.user.FindPasswordDto;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ResponseEntity<String> findIdCheckAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto) {
        try {
            if (checkEmailAuthCode(checkEmailAuthCodeDto)) {
                String email = checkEmailAuthCodeDto.getEmail();
                String accountId = userRepository.findByEmail(email).getAccountId();

                return new ResponseEntity<>(accountId, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> findPasswordCheckAuthCode(FindPasswordCheck findPasswordCheck) {
        String accountId = findPasswordCheck.getAccountId();
        Optional<User> user = userRepository.findByAccountId(accountId);

        if (user.isPresent()) {
            String email = user.get().getEmail();
            String code = findPasswordCheck.getCode();

            if (mailService.checkAuthCode(email, code)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    // PUT

    // DELETE

    // ELSE
    @Override
    public Boolean checkEmailAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto) {
        String email = checkEmailAuthCodeDto.getEmail();
        String code = checkEmailAuthCodeDto.getCode();

        return mailService.checkAuthCode(email, code);
    }

}
