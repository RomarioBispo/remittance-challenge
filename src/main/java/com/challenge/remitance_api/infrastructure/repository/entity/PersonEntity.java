package com.challenge.remitance_api.infrastructure.repository.entity;

import com.challenge.remitance_api.businessrule.domain.enums.PersonTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Enumerated(EnumType.STRING)
    private PersonTypeEnum personType;
}