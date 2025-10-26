package com.silvionetto.demo.service;

import com.silvionetto.demo.model.DemoRole;
import com.silvionetto.demo.model.DemoUser;
import com.silvionetto.demo.repository.DemoRoleRepository;
import com.silvionetto.demo.repository.DemoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DemoUserService {

    @Autowired
    private DemoUserRepository demoUserRepository;
    @Autowired
    private DemoRoleRepository demoRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public DemoUser registerNewUser(DemoUser demoUser) {
        demoUser.setPassword(passwordEncoder.encode(demoUser.getPassword()));
        Optional<DemoRole> demoRole = demoRoleRepository.findByName("USER");
        demoRole.ifPresent(demoUser::addRoles);
        return demoUserRepository.save(demoUser);
    }

    public boolean isUserNameTaken(String userName) {
        return demoUserRepository.findByUserName(userName).isPresent();
    }

    public boolean isEmailTaken(String email) {
        return demoUserRepository.findByEmail(email).isPresent();
    }

    public List<DemoUser> findAll() {
        return demoUserRepository.findAll();
    }

    public DemoUser findById(Long id) {
        return demoUserRepository.findById(id).orElse(null);
    }

    public DemoUser save(DemoUser demoUser) {
        demoUser.setPassword(passwordEncoder.encode(demoUser.getPassword()));
        return demoUserRepository.save(demoUser);
    }

    public void deleteById(Long id) {
        demoUserRepository.deleteById(id);
    }

    public Optional<DemoUser> findByUserName(String userName) {
        return demoUserRepository.findByUserName(userName);
    }
}
