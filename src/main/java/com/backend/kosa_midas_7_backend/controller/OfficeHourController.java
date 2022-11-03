package com.backend.kosa_midas_7_backend.Controller;

import com.backend.kosa_midas_7_backend.entity.dto.officehour.CheckDto;
import com.backend.kosa_midas_7_backend.entity.dto.officehour.WorkDto;
import com.backend.kosa_midas_7_backend.service.officehoure.OfficeHourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/officehour")
public class OfficeHourController {

    private final OfficeHourService officeHourService;

    // GET
    @GetMapping
    public ResponseEntity<LocalDateTime> checkWorkHourBeforeEnd(@RequestBody CheckDto checkDto) {
        return officeHourService.checkWorkHourBeforeEnd(checkDto);
    }

    @GetMapping("/check-work-hour-end")
    public ResponseEntity<Map<String, String>> checkMyWork(@RequestBody CheckDto checkDto) {
        return officeHourService.checkWorkHour(checkDto);
    }

    // POST
    @PostMapping("/start-work")
    public ResponseEntity<HttpStatus> startWork(@RequestBody WorkDto workDto) {
        return officeHourService.startWork(workDto);
    }

    @PostMapping("/end-work")
    public ResponseEntity<HttpStatus> endWork(@RequestBody WorkDto workDto) {
        return officeHourService.endWork(workDto);
    }

    // PUT

    // DELETE

    // ELSE

}
