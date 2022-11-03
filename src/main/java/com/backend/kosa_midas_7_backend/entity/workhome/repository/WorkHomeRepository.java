package com.backend.kosa_midas_7_backend.entity.workhome.repository;

import com.backend.kosa_midas_7_backend.entity.user.User;
import com.backend.kosa_midas_7_backend.entity.workhome.WorkHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WorkHomeRepository extends JpaRepository<WorkHome, Long> {

    Optional<WorkHome> findByUserAndEndDateAfterOrEndDateAndRecruitment(User user, LocalDate nowtime, LocalDate time, Boolean recruitment);

}
