package com.silvionetto.demo.repository;

import com.silvionetto.demo.model.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemoUserRepository extends JpaRepository<DemoUser, Long> {
    Optional<DemoUser> findByUserName(String userName);
    Optional<DemoUser> findByEmail(String email);
}