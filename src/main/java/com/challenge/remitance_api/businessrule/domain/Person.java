package com.challenge.remitance_api.businessrule.domain;

import com.challenge.remitance_api.businessrule.domain.enums.PersonTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @NotNull
    private Integer id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private PersonTypeEnum personType;
    private LocalDateTime createdAt;
}
