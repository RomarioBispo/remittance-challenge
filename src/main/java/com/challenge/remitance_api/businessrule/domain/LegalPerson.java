package com.challenge.remitance_api.businessrule.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LegalPerson extends Person{
    @NotNull
    private Integer id;
    @NotNull
    private String companyName;
    @NotNull
    private String cnpj;
}
