package com.challenge.remitance_api.infrastructure.repository.entity;

import com.challenge.remitance_api.businessrule.domain.enums.PersonTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;
    @Column(name = "person_type")
    private PersonTypeEnum personType;
    @Column(name = "create_at")
    private LocalDateTime createdAt;
}