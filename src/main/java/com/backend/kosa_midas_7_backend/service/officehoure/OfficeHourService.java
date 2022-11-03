package com.backend.kosa_midas_7_backend.service.officehoure;

import com.backend.kosa_midas_7_backend.dto.request.CheckDto;
import com.backend.kosa_midas_7_backend.dto.request.WorkDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public interface OfficeHourService {

    // GET
    ResponseEntity<LocalDateTime> checkWorkHourBeforeEnd(CheckDto checkDto);

    ResponseEntity<Map<String, String>> checkWorkHour(CheckDto checkDto);

    // POST
    ResponseEntity<HttpStatus> startWork(WorkDto workDto);

    ResponseEntity<HttpStatus> endWork(WorkDto workDto);

    // PUT

    // DELETE

    // ELSE

}
