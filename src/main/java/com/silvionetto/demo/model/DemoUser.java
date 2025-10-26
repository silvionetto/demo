package com.silvionetto.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemoUser extends DemoEntity {
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "demo_user_roles",
            joinColumns = @JoinColumn(name = "demo_user_id"),
            inverseJoinColumns = @JoinColumn(name = "demo_role_id"))
    private Set<DemoRole> roles = new HashSet<>();

    public void addRoles(DemoRole role) {
        roles.add(role);
    }
}
