package com.backend.kosa_midas_7_backend.service.officehoure;

import com.backend.kosa_midas_7_backend.dto.request.CheckDto;
import com.backend.kosa_midas_7_backend.dto.request.WorkDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfficeHourServiceImpl implements OfficeHourService {

    private final UserRepository userRepository;

    private final OfficeHourRepository officeHourRepository;

    private final WorkHomeRepository workHomeRepository;

    private final long WORK_HOUR = 28800;

    // GET
    @Override
    public ResponseEntity<LocalDateTime> checkWorkHourBeforeEnd(CheckDto checkDto) {
        try {
            Long userId = checkDto.getUserId();
            Optional<User> user = userRepository.findById(userId);

            if (user.isPresent()) {
                Optional<OfficeHour> officeHour = officeHourRepository.findByDate(user.get().getDate());

                if (officeHour.isPresent()) {
                    LocalDateTime startTime = officeHour.get().getStartOfficeHours();

                    return new ResponseEntity<>(startTime, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> checkWorkHour(CheckDto checkDto) {
        try {
            Long userId = checkDto.getUserId();
            Optional<User> user = userRepository.findById(userId);

            if (user.isPresent()) {
                Optional<OfficeHour> officeHour = officeHourRepository.findByDate(user.get().getDate() - 1);

                if (officeHour.isPresent()) {
                    OfficeHour officeHourEntity = officeHour.get();
                    LocalDateTime startTime = officeHourEntity.getStartOfficeHours();
                    LocalDateTime endTime = officeHourEntity.getFinishOfficeHours();

                    long startTimeSecond = startTime.toEpochSecond(ZoneOffset.UTC);
                    long endTimeSecond = endTime.toEpochSecond(ZoneOffset.UTC);
                    long checkTime = endTimeSecond - startTimeSecond;

                    int totalWorkHour = (int) (checkTime - WORK_HOUR);
                    int absTime = Math.abs(totalWorkHour);

                    int hours = absTime / 3600;
                    int minutes = absTime % 3600 / 60;

                    String time;
                    Map<String, String> mapTime = new HashMap<>();

                    if (totalWorkHour >= 0) {
                        time = String.format("%02d:%02d", hours, minutes);
                    } else {
                        time = String.format("-%02d:%02d", hours, minutes);
                    }
                    mapTime.put("time", time);
                    return new ResponseEntity<>(mapTime, HttpStatus.OK);
                } else {
                    log.info("1");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                log.info("2");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST
    @Override
    public ResponseEntity<HttpStatus> startWork(WorkDto workDto) {
        try {
            Long userId = workDto.getUserId();
            Optional<User> user = userRepository.findById(userId);

            if (user.isPresent()) {
                User userEntity = user.get();

                String coreStart = userEntity.getCoreTimeStart();
                String coreEnd = userEntity.getCoreTimeEnd();

                LocalTime coreStartLT = LocalTime.parse(coreStart);
                LocalTime coreEndLT = LocalTime.parse(coreEnd);
                LocalTime nowTime = LocalTime.now();

                log.info("coreStartLT: {}, coreEndLT: {}, now: {}", coreStartLT, coreEndLT, LocalTime.now());

                if (nowTime.compareTo(coreStartLT) == -1 || coreEndLT.compareTo(nowTime) == -1) {
                    userEntity.setWarning(userEntity.getWarning() + 1);
                }

                Optional<WorkHome> workHome = workHomeRepository.findByUserAndRecruitmentAndEndDateAfterOrEndDate(userEntity, true, LocalDate.now(), LocalDate.now());

                OfficeHour officeHour = new OfficeHour();
                officeHour = officeHour.startWork(officeHour, userEntity);

                if (workHome.isPresent()) {
                    WorkHome workHomeEntity = workHome.get();
                    officeHour.setOfficeHoursType("재택근무");
                    officeHour.setWorkHome(workHomeEntity);
                } else {
                    officeHour.setOfficeHoursType("회사");
                }

                officeHourRepository.save(officeHour);

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> endWork(WorkDto workDto) {
        try {
            Long userId = workDto.getUserId();
            Optional<User> user = userRepository.findById(userId);

            if (user.isPresent()) {
                User userEntity = user.get();
                Optional<OfficeHour> officeHour = officeHourRepository.findByDate(userEntity.getDate());

                if (officeHour.isPresent()) {
                    OfficeHour officeHourEntity = officeHour.get();

                    officeHourEntity.setFinishOfficeHours(LocalDateTime.now());
                    officeHourRepository.save(officeHourEntity);

                    userEntity.setDate(userEntity.getDate() + 1);
                    userRepository.save(userEntity);

                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    log.info("1");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                log.info("2");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT

    // DELETE

    // ELSE

}
