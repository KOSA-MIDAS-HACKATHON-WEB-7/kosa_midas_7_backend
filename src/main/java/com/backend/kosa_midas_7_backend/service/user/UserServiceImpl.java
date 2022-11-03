package com.backend.kosa_midas_7_backend.service.user;

import com.backend.kosa_midas_7_backend.dto.request.*;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import com.backend.kosa_midas_7_backend.entity.workhome.repository.WorkHomeRepository;
import com.backend.kosa_midas_7_backend.exception.UserNotFound;
import com.backend.kosa_midas_7_backend.security.jwt.JwtProvider;
import com.backend.kosa_midas_7_backend.service.mail.MailService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MailService mailService;

    private final WorkHomeRepository workHomeRepository;

    private final JwtProvider jwtProvider;

    // GET
    @Override
    public ResponseEntity<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<User>> findAllUser() {
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    @Override
    public UserDto getUserInfo(String token) {
        String accountId = jwtProvider.parseClaims(token).getSubject();
        User user = userRepository.findByAccountId(accountId).orElseThrow(() ->{
            throw UserNotFound.EXCEPTION;
        });
        return UserDto.builder()
                .userName(user.getUserName())
                .role(user.getRole().toString())
                .accountId(user.getAccountId())
                .department(user.getDepartment())
                .email(user.getEmail())
                .password(null)
                .position(user.getPosition())
                .build();
    }

    // POST


    @Override
    public ResponseEntity<WorkHome> findWorkHome(FindWorkHomeDto findWorkHomeDto) {
        Optional<User> user = userRepository.findById(findWorkHomeDto.getUserId());

        if (user.isPresent()) {
            Optional<WorkHome> workHome = workHomeRepository.findByUser(user.get());

            if (workHome.isPresent()) {
                return new ResponseEntity<>(workHome.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> findPassword(FindPasswordDto findPasswordDto) throws Exception {
        String accountId = findPasswordDto.getAccountId();

        User user = userRepository.findByAccountId(accountId).orElseThrow(() -> {
            throw UserNotFound.EXCEPTION;
        });
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
    public ResponseEntity<Boolean> signUpCheck(CheckEmailAuthCodeDto emailAuthCodeDto) {
        String email = emailAuthCodeDto.getEmail();
        String code = emailAuthCodeDto.getCode();

        if (mailService.checkAuthCode(email, code)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
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

    @Override
    public ResponseEntity<WorkHome> workHomeApplication(WorkHomeApplicationDto workHomeApplicationDto) {
        try {
            Long userId = workHomeApplicationDto.getUserId();
            Optional<User> user = userRepository.findById(userId);

            if (user.isPresent()) {
                User userEntity = user.get();
                Optional<WorkHome> workHomeCheck = workHomeRepository.findByUser(userEntity);
                WorkHome workHome = new WorkHome();

                if (workHomeCheck.isPresent()) {
                    log.info("change: {}", workHomeCheck.get().getId());
                    workHomeApplicationDto.setId(workHomeCheck.get().getId());
                    workHome.createWorkHome(userEntity, workHomeApplicationDto);
                } else {
                    workHome.createWorkHome(userEntity, workHomeApplicationDto);
                }
                workHomeRepository.save(workHome);
                return new ResponseEntity<>(workHome, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.info("error: {}",exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // PUT
    @Override
    public ResponseEntity<User> changeCoreTime(ChangeCoreTimeDto changeCoreTimeDto) {
        Long userId = changeCoreTimeDto.getUserId();
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User userEntity = user.get();
            userEntity.setCoreTimeStart(changeCoreTimeDto.getCoreTimeStart());
            userEntity.setCoreTimeEnd(changeCoreTimeDto.getCoreTimeEnd());

            userRepository.save(userEntity);

            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE

    // ELSE
    @Override
    public Boolean checkEmailAuthCode(CheckEmailAuthCodeDto checkEmailAuthCodeDto) {
        String email = checkEmailAuthCodeDto.getEmail();
        String code = checkEmailAuthCodeDto.getCode();

        return mailService.checkAuthCode(email, code);
    }

}
