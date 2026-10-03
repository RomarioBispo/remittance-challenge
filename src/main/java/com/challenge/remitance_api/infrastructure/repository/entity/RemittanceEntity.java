package com.challenge.remitance_api.infrastructure.repository.entity;

import com.challenge.remitance_api.businessrule.domain.CurrencyEnum;
import com.challenge.remitance_api.businessrule.domain.RemittanceStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="remittance")
@Builder
public class RemittanceEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "debitor_wallet_id")
    private Integer debitorWalletId;

    @Column(name = "creditor_wallet_id")
    private Integer creditorWalletId;

    @NotBlank
    private Double value;

    @NotBlank
    private CurrencyEnum currency;

    @NotBlank
    private RemittanceStatusEnum status;

    @Column(name = "remittance_date")
    private LocalDateTime remittanceDate;
}
