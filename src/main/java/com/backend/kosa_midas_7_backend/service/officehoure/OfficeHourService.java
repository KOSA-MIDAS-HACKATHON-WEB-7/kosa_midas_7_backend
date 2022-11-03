package com.backend.kosa_midas_7_backend.service.officehoure;

import com.backend.kosa_midas_7_backend.entity.dto.officehour.WorkDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OfficeHourService {

    // GET

    // POST
    ResponseEntity<HttpStatus> startWork(WorkDto workDto);

    // PUT

    // DELETE

    // ELSE

}
