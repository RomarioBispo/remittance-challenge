package com.challenge.remitance_api.infrastructure.gateway;

import com.challenge.remitance_api.businessrule.domain.CurrencyEnum;
import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.businessrule.gateway.RemittanceGateway;
import com.challenge.remitance_api.infrastructure.mapper.PersonMapper;
import com.challenge.remitance_api.infrastructure.mapper.RemittanceMapper;
import com.challenge.remitance_api.infrastructure.repository.PersonRepository;
import com.challenge.remitance_api.infrastructure.repository.RemittanceRepository;
import com.challenge.remitance_api.infrastructure.repository.WalletRepository;
import com.challenge.remitance_api.infrastructure.repository.entity.RemittanceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RemittanceGatewayImpl implements RemittanceGateway {

    private final WalletRepository walletRepository;
    private final RemittanceRepository remittanceRepository;
    private final RemittanceMapper remittanceMapper;

    @Override
    public Double findWalletBalanceInReaisByPersonId(Integer personId) {
        return walletRepository.findReaisBalanceByPersonId(personId);
    }

    @Override
    public Double findDailyLimit(Integer personId) {
        return remittanceRepository.findDailyTotalByPersonId(personId);
    }

    @Override
    public Remittance createRemmitance(Integer debitorWalletId, Integer creditorWalletId,
                                       Double value, CurrencyEnum currency) {
        Remittance r = Remittance.builder()
                        .debitorId(debitorWalletId)
                        .creditorId(creditorWalletId)
                        .value(value)
                        .currency(currency)
                        .remittanceDate(LocalDateTime.now())
                .build();
        RemittanceEntity entity = remittanceRepository.save(remittanceMapper.toEntity(r));
        return remittanceMapper.toDomain(entity);
    }
}
