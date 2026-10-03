package com.challenge.remitance_api.infrastructure.controller;

import com.challenge.remitance_api.businessrule.domain.Person;
import com.challenge.remitance_api.businessrule.usecase.CreateRemittanceUseCase;
import com.challenge.remitance_api.infrastructure.controller.request.RemittanceRequest;
import com.challenge.remitance_api.infrastructure.mapper.RemittanceMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remittances/v1/remittances")
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final CreateRemittanceUseCase remittanceUseCase;
    private final RemittanceMapper mapper;

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody RemittanceRequest remittanceRequest){

        remittanceUseCase.execute(mapper.toDomain(remittanceRequest));

        return ResponseEntity.noContent().build();
    }
}
