package com.backend.kosa_midas_7_backend.entity.officehour.repository;

import com.backend.kosa_midas_7_backend.entity.officehour.OfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeHourRepository extends JpaRepository<OfficeHour, Long> {

    Optional<OfficeHour> findByDate(int date);

    List<OfficeHour> findAllByCreatedAtAfterOrCreatedAtAndCreatedAtBeforeOrCreatedAt(LocalDateTime time1, LocalDateTime time2, LocalDateTime time3, LocalDateTime time4);

}
