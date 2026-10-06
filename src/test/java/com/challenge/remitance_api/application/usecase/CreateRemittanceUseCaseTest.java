package com.challenge.remitance_api.application.usecase;

import com.challenge.remitance_api.businessrule.domain.CurrencyEnum;
import com.challenge.remitance_api.businessrule.domain.Person;
import com.challenge.remitance_api.businessrule.domain.Remittance;
import com.challenge.remitance_api.businessrule.domain.Wallet;
import com.challenge.remitance_api.businessrule.domain.enums.PersonTypeEnum;
import com.challenge.remitance_api.businessrule.gateway.CurrentQuoteGateway;
import com.challenge.remitance_api.businessrule.gateway.PersonGateway;
import com.challenge.remitance_api.businessrule.gateway.RemittanceGateway;
import com.challenge.remitance_api.businessrule.gateway.WalletGateway;
import com.challenge.remitance_api.infrastructure.exception.DailyLimitReachedException;
import com.challenge.remitance_api.infrastructure.exception.NotEnoughBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateRemittanceUseCaseTest {

    @Mock
    private RemittanceGateway remittanceGateway;

    @Mock
    private PersonGateway personGateway;

    @Mock
    private CurrentQuoteGateway currentQuoteGateway;

    @Mock
    private WalletGateway walletGateway;

    @InjectMocks
    private CreateRemittanceUseCaseImpl createRemittanceUseCase;

    @Test
    void shouldNotCreateRemittanceWhenNaturalPersonHasNoEnoughBalance(){
        Remittance remittance = Remittance.builder()
                .debitorId(1)
                .creditorId(2)
                .value(1000.00)
                .currency(CurrencyEnum.BRL)
                .remittanceDate(LocalDateTime.now())
                .build();

        when(remittanceGateway.findWalletBalanceInReaisByPersonId(any()))
                .thenReturn(500.00);

        assertThrows(NotEnoughBalanceException.class, () -> {
            createRemittanceUseCase.execute(remittance);
        });
    }

    @Test
    void shouldNotCreateRemittanceWhenNaturalPersonHasReachedDailyLimit(){
        Remittance remittance = Remittance.builder()
                .debitorId(1)
                .creditorId(2)
                .value(7000.00)
                .currency(CurrencyEnum.BRL)
                .remittanceDate(LocalDateTime.now())
                .build();

        when(remittanceGateway.findWalletBalanceInReaisByPersonId(any()))
                .thenReturn(7500.00);

        Mockito.when(remittanceGateway.findDailyLimit(any())).thenReturn(4000.00);

        Person mockedPerson = new Person(1, "example@mail.com",
                "password", PersonTypeEnum.PF);

        Mockito.when(personGateway.findPersonById(any())).thenReturn(mockedPerson);

        assertThrows(DailyLimitReachedException.class, () -> {
            createRemittanceUseCase.execute(remittance);
        });
    }

    @Test
    void shouldCreateRemittanceHappyPath(){
        Remittance remittance = Remittance.builder()
                .debitorId(1)
                .creditorId(2)
                .value(500.00)
                .currency(CurrencyEnum.BRL)
                .remittanceDate(LocalDateTime.now())
                .build();

        Person mockedPerson = new Person(1, "example@mail.com",
                "password", PersonTypeEnum.PF);

        Wallet debitorWallet = new Wallet(1, 1, 2000.00, 0.00);
        Wallet creditorWallet = new Wallet(2, 2, 0.00, 100.00);

        when(remittanceGateway.findWalletBalanceInReaisByPersonId(1)).thenReturn(2000.00);
        when(remittanceGateway.findDailyLimit(1)).thenReturn(1000.00);
        when(personGateway.findPersonById(1)).thenReturn(mockedPerson);

        when(currentQuoteGateway.getQuote()).thenReturn(5.00);

        when(walletGateway.findByPersonId(1)).thenReturn(debitorWallet);
        when(walletGateway.findByPersonId(2)).thenReturn(creditorWallet);
        when(walletGateway.save(any(Wallet.class))).thenReturn(debitorWallet); // Ajustado para retornar um objeto válido

        when(remittanceGateway.createRemmitance(
                eq(remittance.getDebitorId()),
                eq(remittance.getCreditorId()),
                eq(remittance.getValue()),
                eq(remittance.getCurrency())
        )).thenReturn(remittance);

        Remittance result = createRemittanceUseCase.execute(remittance);

        assertNotNull(result);

        verify(walletGateway, times(2)).save(any(Wallet.class));
        verify(remittanceGateway, times(1))
                .createRemmitance(
                        eq(remittance.getDebitorId()),
                        eq(remittance.getCreditorId()),
                        eq(remittance.getValue()),
                        eq(remittance.getCurrency())
                );
    }
}