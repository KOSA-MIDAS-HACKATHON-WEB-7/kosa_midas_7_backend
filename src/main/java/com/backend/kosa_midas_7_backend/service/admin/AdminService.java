package com.backend.kosa_midas_7_backend.service.admin;

import com.backend.kosa_midas_7_backend.dto.request.UpdateInfoAdmin;
import com.backend.kosa_midas_7_backend.dto.request.WorkHomeResponse;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdateAccountId;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdateDepartment;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdatePosition;
import com.backend.kosa_midas_7_backend.entity.user.Role;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import com.backend.kosa_midas_7_backend.entity.workhome.repository.WorkHomeRepository;
import com.backend.kosa_midas_7_backend.exception.AcceptUnauthorized;
import com.backend.kosa_midas_7_backend.exception.UserNotFound;
import com.backend.kosa_midas_7_backend.exception.WorkHomeNotFound;
import com.backend.kosa_midas_7_backend.security.auth.Details;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final WorkHomeRepository workHomeRepository;

    private final PasswordEncoder passwordEncoder;

    public void updatePassword(UpdatePassword updatePassword) {
        User user = validateAdmin(updatePassword.getAccountId());
        userRepository.save(user.changePassword(passwordEncoder.encode(updatePassword.getPassword())));
    }

    public void updateDepartment(UpdateDepartment updateDepartment) {
        User user = validateAdmin(updateDepartment.getAccountId());
        userRepository.save(user.changeDepartment(updateDepartment.getDepartment()));
    }

    public void updatePosition(UpdatePosition updatePosition) {
        User user = validateAdmin(updatePosition.getAccountId());
        userRepository.save(user.changePosition(updatePosition.getPosition()));
    }

    public void updateAccountId(UpdateAccountId updateAccountId) {
        User user = validateAdmin(updateAccountId.getAccountId());
        userRepository.save(user.changeAccountId(updateAccountId.getNewAccountId()));
    }


    public void updateInfo(UpdateInfoAdmin updateInfoAdmin) {
        User user = validateAdmin(updateInfoAdmin.getAccountId());
        if (updateInfoAdmin.getPassword() != null)
            user.changePassword(passwordEncoder.encode(updateInfoAdmin.getPassword()));
        if(updateInfoAdmin.getDepartment() != null)
            user.changeDepartment(updateInfoAdmin.getDepartment());
        if(updateInfoAdmin.getPosition() != null)
            user.changeDepartment(updateInfoAdmin.getDepartment());
        if(updateInfoAdmin.getNewAccountId() != null)
            user.changeAccountId(updateInfoAdmin.getNewAccountId());
        userRepository.save(user);
    }

    public void acceptSignUp(String accountId) {
        User user = validateAdmin(accountId);
        userRepository.save(user.changeAccept(true));
    }

    public void acceptWorkHome(Long id) {
        validate();
        WorkHome workHome = workHomeRepository.findById(id).orElseThrow(() -> {
           throw WorkHomeNotFound.EXCEPTION;
        });
        workHomeRepository.save(workHome.updateRecruitment(true));
    }

    public void refuseWorkHome(Long id, WorkHomeResponse response) {
        validate();
        WorkHome workHome = workHomeRepository.findById(id).orElseThrow(() -> {
            throw WorkHomeNotFound.EXCEPTION;
        });
        workHome.updateRecruitment(false);
        workHome.updateResponse(response.getWorkHomeResponse());
        workHomeRepository.save(workHome);
    }

    private User validateAdmin(String accountId) {
        User user = userRepository.findByAccountId(accountId).orElseThrow(() -> {
            throw UserNotFound.EXCEPTION;
        });
        Details a = (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(a.getUser().getRole() == Role.ADMIN)) {
            throw AcceptUnauthorized.EXCEPTION;
        }
        return user;
    }

    private void validate() {
        Details a =(Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(a.getUser().getRole() == Role.ADMIN)) {
            throw AcceptUnauthorized.EXCEPTION;
        }
    }

    public ResponseEntity<List<WorkHome>> getWorkHomeList() {
        List<WorkHome> workHomeList = workHomeRepository.findAllByStartDateAfterOrStartDate(LocalDate.now(), LocalDate.now());

        if (workHomeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(workHomeList, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<User>> getSignUpList() {
        List<User> userList = userRepository.findAllByAccept(false);

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    public ResponseEntity<HttpStatus> deleteSignUpApplication(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
