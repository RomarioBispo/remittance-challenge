package com.challenge.remitance_api.infrastructure.mapper;

import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.infrastructure.controller.request.RemittanceRequest;
import com.challenge.remitance_api.infrastructure.repository.entity.RemittanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemittanceMapper {
    @Mapping(source = "debitorId", target = "debitorWalletId")
    @Mapping(source = "creditorId", target = "creditorWalletId")
    RemittanceEntity toEntity(Remittance remittance);
    Remittance toDomain(RemittanceRequest remittance);
    Remittance toDomain(RemittanceEntity remittance);
}
