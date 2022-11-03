package com.backend.kosa_midas_7_backend.entity.officehour.repository;

import com.backend.kosa_midas_7_backend.entity.officehour.OfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeHourRepository extends JpaRepository<OfficeHour, Long> {
}
