package com.challenge.remitance_api.application.usecase;

import com.challenge.remitance_api.businessrule.domain.Person;
import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.businessrule.domain.enums.PersonTypeEnum;
import com.challenge.remitance_api.businessrule.gateway.CurrentQuoteGateway;
import com.challenge.remitance_api.businessrule.gateway.PersonGateway;
import com.challenge.remitance_api.businessrule.gateway.RemittanceGateway;
import com.challenge.remitance_api.businessrule.usecase.CreateRemittanceUseCase;
import com.challenge.remitance_api.infrastructure.exception.DailyLimitReachedException;
import com.challenge.remitance_api.infrastructure.exception.NotEnoughBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRemittanceUseCaseImpl implements CreateRemittanceUseCase {

    //TODO: convert in parameters yaml or database
    public static final int NATURAL_PERSON_DAILY_LIMIT = 10000;
    public static final int LEGAL_PERSON_DAILY_LIMIT = 50000;
    private final RemittanceGateway remittanceGateway;
    private final PersonGateway personGateway;
    private final CurrentQuoteGateway currentQuoteGateway;

    @Override
    public Remittance execute(Remittance remittance) {
        Double value = remittance.getValue();
        verifyBalance(remittance.getDebitorId(), value);
        verifyDailyLimit(remittance.getDebitorId(), value);
        var dollarQuotation = verifyDollarQuotation();
        //persist remitance
        return null;
    }

    private void verifyDailyLimit(Integer debitorId, Double value) {
        Double dailyLimit = remittanceGateway.findDailyLimit(debitorId);
        Person person = personGateway.findPersonById(debitorId);
        if (PersonTypeEnum.PF.equals(person.getPersonType()) && (value + dailyLimit > NATURAL_PERSON_DAILY_LIMIT)){
            throw new DailyLimitReachedException("Daily limit for natural person is reached: " + NATURAL_PERSON_DAILY_LIMIT);
        }

        if (PersonTypeEnum.PJ.equals(person.getPersonType()) && (value + dailyLimit > LEGAL_PERSON_DAILY_LIMIT)){
            throw new DailyLimitReachedException("Daily limit for legal person is reached: " + LEGAL_PERSON_DAILY_LIMIT);
        }
    }

    private void verifyBalance(Integer personId, Double remittanceValue) {
        Double balance = remittanceGateway.findWalletBalanceInReaisByPersonId(personId);
        if (remittanceValue > balance) {
            throw new NotEnoughBalanceException("Not enough Balance to continue the remittance, aborting");
        }
    }

    private Double verifyDollarQuotation() {
      return currentQuoteGateway.getQuote();
    }

}
