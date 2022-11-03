package com.backend.kosa_midas_7_backend.entity.refresh.repository;

import com.backend.kosa_midas_7_backend.entity.refresh.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<Refresh, String> {
}
