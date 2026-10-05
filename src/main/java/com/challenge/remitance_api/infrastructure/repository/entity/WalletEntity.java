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
@Entity(name="wallet")
@Builder
public class WalletEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "person_id")
    private Integer personId;
    @Column(name = "reais_balance")
    private Double reaisBalance;
    @Column(name = "dollar_balance")
    private Double dollarBalance;
}
