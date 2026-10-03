package com.challenge.remitance_api.infrastructure.gateway;

import com.challenge.remitance_api.businessrule.gateway.RemittanceGateway;
import com.challenge.remitance_api.infrastructure.mapper.PersonMapper;
import com.challenge.remitance_api.infrastructure.repository.PersonRepository;
import com.challenge.remitance_api.infrastructure.repository.RemittanceRepository;
import com.challenge.remitance_api.infrastructure.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RemittanceGatewayImpl implements RemittanceGateway {

    private final WalletRepository walletRepository;
    private final RemittanceRepository remittanceRepository;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Double findWalletBalanceInReaisByPersonId(Integer personId) {
        //TODO: validate personid exists
        return walletRepository.findReaisBalanceByPersonId(personId);
    }

    @Override
    public Double findDailyLimit(Integer personId) {
        return remittanceRepository.findDailyTotalByPersonId(personId);
    }
}
