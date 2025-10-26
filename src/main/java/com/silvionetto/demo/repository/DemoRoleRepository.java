package com.silvionetto.demo.repository;

import com.silvionetto.demo.model.DemoRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemoRoleRepository extends JpaRepository<DemoRole, Long> {
    Optional<DemoRole> findByName(String name);
}
