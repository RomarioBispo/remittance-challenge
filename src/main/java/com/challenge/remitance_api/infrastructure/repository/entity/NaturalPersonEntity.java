package com.challenge.remitance_api.infrastructure.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="natural_person")
public class NaturalPersonEntity extends PersonEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    private String cpf;
}
