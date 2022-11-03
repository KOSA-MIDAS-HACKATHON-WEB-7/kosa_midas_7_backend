package com.backend.kosa_midas_7_backend.repository;

import com.backend.kosa_midas_7_backend.entity.OfficeHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeHourRepository extends JpaRepository<OfficeHour, Long> {
}
