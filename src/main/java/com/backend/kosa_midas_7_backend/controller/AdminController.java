package com.backend.kosa_midas_7_backend.controller;

import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdateDepartment;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePosition;
import com.backend.kosa_midas_7_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePassword(@RequestBody UpdatePassword updatePassword) {
        service.updatePassword(updatePassword);
    }

    @PutMapping("/department")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateDepartment(@RequestBody UpdateDepartment updateDepartment) {
        service.updateDepartment(updateDepartment);
    }

    @PutMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePosition(@RequestBody UpdatePosition updatePosition) {
        service.updatePosition(updatePosition);
    }
}
