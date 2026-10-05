package com.challenge.remitance_api.infrastructure.controller;

import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.businessrule.usecase.CreateRemittanceUseCase;
import com.challenge.remitance_api.infrastructure.controller.request.RemittanceRequest;
import com.challenge.remitance_api.infrastructure.mapper.RemittanceMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remittances/v1/remittances")
@RequiredArgsConstructor
@Slf4j
public class RemittanceController {
    private final CreateRemittanceUseCase remittanceUseCase;
    private final RemittanceMapper mapper;

    @PostMapping
    public ResponseEntity<Remittance> create(@Valid @RequestBody RemittanceRequest remittanceRequest){

        Remittance remittance = remittanceUseCase.execute(mapper.toDomain(remittanceRequest));

        return ResponseEntity.status(201).body(remittance);
    }
}
