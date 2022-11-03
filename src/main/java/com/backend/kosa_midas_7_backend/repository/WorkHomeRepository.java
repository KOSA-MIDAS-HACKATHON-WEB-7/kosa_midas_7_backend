package com.backend.kosa_midas_7_backend.repository;

import com.backend.kosa_midas_7_backend.entity.User;
import com.backend.kosa_midas_7_backend.entity.WorkHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkHomeRepository extends JpaRepository<WorkHome, Long> {

    Optional<WorkHome> findByUser(User user);

}
