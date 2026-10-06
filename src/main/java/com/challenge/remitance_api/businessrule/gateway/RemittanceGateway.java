package com.challenge.remitance_api.businessrule.gateway;

import com.challenge.remitance_api.businessrule.domain.CurrencyEnum;
import com.challenge.remitance_api.businessrule.domain.Remittance;

public interface RemittanceGateway {
    Double findWalletBalanceInReaisByPersonId(Integer personId);
    Double findDailyLimit(Integer personId);
    Remittance createRemmitance(Integer debitorWalletId, Integer creditorWalletId,
                                Double value,
                                CurrencyEnum currency);
}
