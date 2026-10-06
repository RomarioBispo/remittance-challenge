package com.challenge.remitance_api.businessrule.usecase;

import com.challenge.remitance_api.businessrule.domain.Remittance;

public interface CreateRemittanceUseCase {
    Remittance execute(Remittance remittance);
}
