package com.challenge.remitance_api.businessrule.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Remittance {
    @NotNull
    private Integer id;
    @NotNull
    private Integer debitorId;
    @NotNull
    private Integer creditorId;

    @NotBlank
    private Double value;

    @NotBlank
    private CurrencyEnum currency;

    @NotBlank
    private RemittanceStatusEnum status;

    @NotBlank
    private LocalDateTime remittanceDate;
}
