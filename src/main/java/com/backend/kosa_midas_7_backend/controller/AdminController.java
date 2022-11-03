package com.backend.kosa_midas_7_backend.controller;

import com.backend.kosa_midas_7_backend.dto.request.UpdateInfoAdmin;
import com.backend.kosa_midas_7_backend.dto.request.WorkHomeResponse;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdateAccountId;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdateDepartment;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.dto.request.admin.UpdatePosition;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import com.backend.kosa_midas_7_backend.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService service;

    @GetMapping("/get-work-home")
    public ResponseEntity<List<WorkHome>> getWorkHomeList() {
        return service.getWorkHomeList();
    }

    @GetMapping("/get-signup-list")
    public ResponseEntity<List<User>> getSignUpList() {
        return service.getSignUpList();
    }

    @DeleteMapping("/delete-signup-application/{id}")
    public ResponseEntity<HttpStatus> deleteSignUpApplication(@PathVariable Long id) {
        return service.deleteSignUpApplication(id);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@RequestBody UpdatePassword updatePassword) {
        service.updatePassword(updatePassword);
    }

    @PutMapping("/department")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartment(@RequestBody UpdateDepartment updateDepartment) {
        service.updateDepartment(updateDepartment);
    }

    @PutMapping("/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePosition(@RequestBody UpdatePosition updatePosition) {
        service.updatePosition(updatePosition);
    }

    @PutMapping("/account-id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountId(@RequestBody UpdateAccountId updateAccountId) {
        service.updateAccountId(updateAccountId);
    }

    @PutMapping("/user-info")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateInfo(@RequestBody UpdateInfoAdmin updateInfoAdmin) {
        service.updateInfo(updateInfoAdmin);
    }

    @PutMapping("/work-home/pos/{id}")
    public void acceptWorkHome(@PathVariable Long id) {
        service.acceptWorkHome(id);
    }

    @PutMapping("/work-home/neg/{id}")
    public void refuseWorkHome(@PathVariable Long id, @RequestBody WorkHomeResponse response) {
        service.refuseWorkHome(id, response);
    }

    @PutMapping("/sign-up/accept/{accountId}")
    public void acceptSignUp(@PathVariable String accountId) {
        service.acceptSignUp(accountId);
    }

}
