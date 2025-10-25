package com.silvionetto.demo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class DemoEntity {
    @Id @GeneratedValue
    private Long id;
    @Version
    private int version = 0;
    @LastModifiedDate
    private Date lastUpdateDate;
}
