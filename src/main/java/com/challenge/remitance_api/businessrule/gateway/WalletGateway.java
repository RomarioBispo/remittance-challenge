package com.challenge.remitance_api.businessrule.gateway;

import com.challenge.remitance_api.businessrule.domain.Wallet;

public interface WalletGateway {
    Wallet findByPersonId(Integer personId);
    Wallet save(Wallet wallet);
}
