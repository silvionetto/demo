package com.silvionetto.demo;

import jakarta.persistence.Entity;

@Entity
public class DemoRole extends DemoEntity {
    private String name;
    private String description;
}
