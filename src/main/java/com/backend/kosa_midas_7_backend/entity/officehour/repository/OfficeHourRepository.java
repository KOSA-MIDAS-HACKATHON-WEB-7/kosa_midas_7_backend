package com.backend.kosa_midas_7_backend.entity.officehour.repository;

import com.backend.kosa_midas_7_backend.entity.officehour.OfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OfficeHourRepository extends JpaRepository<OfficeHour, Long> {

    Optional<OfficeHour> findByDate(int date);

//    Optional<OfficeHour> findByCreatedAtBeforeAndEnd(LocalDateTime nowTime, Boolean end);

//    Optional<OfficeHour> findByCreatedAtBeforeAndFinishOfficeHours(LocalDateTime nowTime, Boolean end);

}
