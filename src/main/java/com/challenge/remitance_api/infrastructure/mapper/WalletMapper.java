package com.challenge.remitance_api.infrastructure.mapper;

import com.challenge.remitance_api.businessrule.domain.Wallet;
import com.challenge.remitance_api.infrastructure.repository.entity.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletEntity toEntity(Wallet wallet);
    Wallet toDomain(WalletEntity walletEntity);
}
