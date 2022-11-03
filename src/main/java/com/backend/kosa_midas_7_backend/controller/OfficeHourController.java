package com.backend.kosa_midas_7_backend.Controller;

import com.backend.kosa_midas_7_backend.entity.dto.officehour.WorkDto;
import com.backend.kosa_midas_7_backend.service.officehoure.OfficeHourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/officehour")
public class OfficeHourController {

    private final OfficeHourService officeHourService;

    // GET

    // POST
    @PostMapping("/start-work")
    public ResponseEntity<HttpStatus> startWork(@RequestBody WorkDto workDto) {
        return officeHourService.startWork(workDto);
    }

    // PUT

    // DELETE

    // ELSE

}
