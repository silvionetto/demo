package com.silvionetto.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoUserRepository extends JpaRepository<DemoUser, Long> {
    DemoUser findByUserName(String userName);
}
