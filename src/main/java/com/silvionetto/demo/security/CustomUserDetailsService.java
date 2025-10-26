package com.silvionetto.demo.security;

import com.silvionetto.demo.model.DemoUser;
import com.silvionetto.demo.repository.DemoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DemoUserRepository demoUserRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DemoUser demoUser = demoUserRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
        return new User(demoUser.getUserName(), demoUser.getPassword(), demoUser.isEnabled(), true, true, true, getAuthorities(demoUser));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(DemoUser demoUser) {
        return demoUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
