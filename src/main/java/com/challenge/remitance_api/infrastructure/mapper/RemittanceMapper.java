package com.challenge.remitance_api.infrastructure.mapper;

import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.infrastructure.controller.request.RemittanceRequest;
import com.challenge.remitance_api.infrastructure.repository.entity.RemittanceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RemittanceMapper {
    RemittanceEntity toEntity(Remittance remittance);
    Remittance toDomain(RemittanceRequest remittance);
}
