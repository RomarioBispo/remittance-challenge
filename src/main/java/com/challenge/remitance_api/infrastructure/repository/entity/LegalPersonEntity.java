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
@Entity(name="legal_person")
public class LegalPersonEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "company_name")
    private String companyName;
    private String cnpj;
}
