package com.backend.kosa_midas_7_backend.controller;

import com.backend.kosa_midas_7_backend.dto2.request.UpdateInfoAdmin;
import com.backend.kosa_midas_7_backend.dto2.request.WorkHomeResponse;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdateAccountId;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdateDepartment;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePosition;
import com.backend.kosa_midas_7_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService service;

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
