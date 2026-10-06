package com.challenge.remitance_api.businessrule.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    @NotNull
    private Integer id;
    @NotNull
    private Integer personId;
    @NotBlank
    private Double reaisBalance;
    @NotBlank
    private Double dollarBalance;
}
