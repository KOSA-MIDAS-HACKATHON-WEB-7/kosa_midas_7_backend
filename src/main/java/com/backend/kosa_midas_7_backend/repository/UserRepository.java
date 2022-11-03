package com.backend.kosa_midas_7_backend.repository;

import com.backend.kosa_midas_7_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);

    User findByEmail(String email);

}
