package com.challenge.remitance_api.businessrule.gateway;

public interface RemittanceGateway {
    Double findWalletBalanceInReaisByPersonId(Integer personId);
    Double findDailyLimit(Integer personId);
}
