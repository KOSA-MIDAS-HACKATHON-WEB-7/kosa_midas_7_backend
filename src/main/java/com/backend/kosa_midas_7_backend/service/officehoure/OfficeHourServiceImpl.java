package com.backend.kosa_midas_7_backend.service.officehoure;

import com.backend.kosa_midas_7_backend.entity.dto.officehour.WorkDto;
import com.backend.kosa_midas_7_backend.entity.officehour.OfficeHour;
import com.backend.kosa_midas_7_backend.entity.officehour.repository.OfficeHourRepository;
import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import com.backend.kosa_midas_7_backend.entity.workhome.repository.WorkHomeRepository;
import com.backend.kosa_midas_7_backend.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfficeHourServiceImpl implements OfficeHourService {

    private final UserRepository userRepository;

    private final OfficeHourRepository officeHourRepository;

    private final WorkHomeRepository workHomeRepository;

    // GET

    // POST
    @Override
    public ResponseEntity<HttpStatus> startWork(WorkDto workDto) {
        Long userId = workDto.getUserId();
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User userEntity = user.get();
            Optional<WorkHome> workHome = workHomeRepository.findByUser(userEntity);

            OfficeHour officeHour = new OfficeHour();
            officeHour = officeHour.startWork(officeHour, userEntity);

            if (workHome.isPresent()) {
                officeHour.setWorkHome(workHome.get());
            }
            officeHourRepository.save(officeHour);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // PUT

    // DELETE

    // ELSE

}
