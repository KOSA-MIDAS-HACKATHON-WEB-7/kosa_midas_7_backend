package com.backend.kosa_midas_7_backend.service.officehoure;

import com.backend.kosa_midas_7_backend.entity.dto.officehour.CheckDto;
import com.backend.kosa_midas_7_backend.entity.dto.officehour.WorkDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface OfficeHourService {

    // GET
    ResponseEntity<LocalDateTime> checkWorkHourBeforeEnd(CheckDto checkDto);

    ResponseEntity<String> checkWorkHour(CheckDto checkDto);

    // POST
    ResponseEntity<HttpStatus> startWork(WorkDto workDto);

    ResponseEntity<HttpStatus> endWork(WorkDto workDto);

    // PUT

    // DELETE

    // ELSE

}
