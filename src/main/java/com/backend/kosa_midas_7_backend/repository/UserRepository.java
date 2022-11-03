package com.backend.kosa_midas_7_backend.repository;

import com.backend.kosa_midas_7_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccountId(String accountId);

}
