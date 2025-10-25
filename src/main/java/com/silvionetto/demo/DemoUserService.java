package com.silvionetto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoUserService {

    @Autowired
    private DemoUserRepository demoUserRepository;

    public List<DemoUser> findAll() {
        return demoUserRepository.findAll();
    }

    public DemoUser findById(Long id) {
        return demoUserRepository.findById(id).orElse(null);
    }

    public DemoUser save(DemoUser demoUser) {
        return demoUserRepository.save(demoUser);
    }

    public void deleteById(Long id) {
        demoUserRepository.deleteById(id);
    }

    public Optional<DemoUser> findByUserName(String userName) {
        return demoUserRepository.findByUserName(userName);
    }
}
