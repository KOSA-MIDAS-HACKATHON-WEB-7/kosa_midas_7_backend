package com.backend.kosa_midas_7_backend.controller;

import com.backend.kosa_midas_7_backend.dto2.request.admin.UpdatePassword;
import com.backend.kosa_midas_7_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService service;

    @PutMapping("/password")
    public void updatePassword(@RequestBody UpdatePassword updatePassword) {
        service.updatePasswordAdmin(updatePassword);
    }

}
