package com.backend.kosa_midas_7_backend.entity.workhome.repository;

import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHomeRepository extends JpaRepository<WorkHome, Long> {
}
