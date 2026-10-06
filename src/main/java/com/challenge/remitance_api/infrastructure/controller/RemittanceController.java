package com.challenge.remitance_api.infrastructure.controller;

import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.businessrule.usecase.CreateRemittanceUseCase;
import com.challenge.remitance_api.infrastructure.controller.request.RemittanceRequest;
import com.challenge.remitance_api.infrastructure.mapper.RemittanceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/remittances/v1/remittances")
@RequiredArgsConstructor
@Slf4j
public class RemittanceController {
    private final CreateRemittanceUseCase remittanceUseCase;
    private final RemittanceMapper mapper;

    @PostMapping
    @Operation(summary = "Create a Remittance", description = "Create a Remittance given creditor id (personid), debitorId (personId), and value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Person Not Found"),
            @ApiResponse(responseCode = "400", description = "Not Enough Balance"),
            @ApiResponse(responseCode = "400", description = "Daily remittance limit reached")
    })
    public ResponseEntity<Remittance> create(@Valid @RequestBody RemittanceRequest remittanceRequest){

        Remittance remittance = remittanceUseCase.execute(mapper.toDomain(remittanceRequest));

        return ResponseEntity.status(201).body(remittance);
    }
}
