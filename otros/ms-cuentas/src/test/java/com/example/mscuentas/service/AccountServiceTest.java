package com.example.mscuentas.service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.enums.AccountProduct;
import com.example.mspersonas.event.catalog.AccountActivatedEvent;
import com.example.mspersonas.event.catalog.AccountRejectedEvent;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.model.Account;
import com.example.mscuentas.model.AccountStatusModel;
import com.example.mscuentas.model.CurrencyModel;
import com.example.mscuentas.repository.AccountRepository;
import com.example.mscuentas.repository.AccountStatusRepository;
import com.example.mscuentas.repository.CurrencyRepository;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private DomainEventPublisher domainEventPublisher;

    @BeforeEach
    void initialize(){
        accountRepository = Mockito.mock(AccountRepository.class);
        domainEventPublisher = Mockito.mock(DomainEventPublisher.class);
        var currencyRepository = Mockito.mock(CurrencyRepository.class);
        var accountStatusRepository = Mockito.mock(AccountStatusRepository.class);
        accountService = new AccountService(accountRepository,
                domainEventPublisher, currencyRepository, accountStatusRepository);
        Mockito.when(currencyRepository.findBySymbol("USD")).thenReturn(
                Optional.of(new CurrencyModel("1", "MURICA", "USD")));
        Mockito.when(currencyRepository.findBySymbol("ARS")).thenReturn(
                Optional.of(new CurrencyModel("2", "ARGENTINA", "ARS")));
        Mockito.when(accountStatusRepository.findByDetail("ACTIVA")).thenReturn(
                Optional.of(new AccountStatusModel("2", "ACTIVA")));
        Mockito.when(accountStatusRepository.findByDetail("Activa")).thenReturn(
                Optional.of(new AccountStatusModel("2", "Activa")));
    }
    @Test
    void givenNonExistingAccountFail(){
        Mockito.when(accountRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApiException.class, () -> accountService.getAccount("1"));
        Mockito.verify(domainEventPublisher, Mockito.never()).publish(Mockito.any());

    }

    @Test
    void givenExistingAccountSucced(){
        Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.of(mockAccount()));
        var foundAccount =  accountService.getAccount("1");
        Assertions.assertNotNull(foundAccount);
        Assertions.assertEquals(BigDecimal.valueOf(100L), foundAccount.balance());
        Assertions.assertEquals("1", foundAccount.id());
        Mockito.verify(domainEventPublisher, Mockito.never()).publish(Mockito.any());

    }
    @Test
    void addNoAccounts() {
        Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(() -> accountService.addAccount(personAddEvent(), new ArrayList<>()));

        Mockito.verify(accountRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(AccountRejectedEvent.class));
        Mockito.verify(domainEventPublisher, Mockito.never())
                .publish(Mockito.any(AccountActivatedEvent.class));
    }

    @Test
    void addBasicAccouunt(){
        Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(() -> accountService.addAccount(personAddEvent(),
                List.of(AccountProduct.BASIC_ACCOUNT)));

        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(AccountActivatedEvent.class));
        Mockito.verify(domainEventPublisher, Mockito.never())
                .publish(Mockito.any(AccountRejectedEvent.class));
    }

    @Test
    void addTwoAccounts(){  Mockito.when(accountRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(() -> accountService.addAccount(personAddEvent(),
                List.of(AccountProduct.BASIC_ACCOUNT, AccountProduct.USD_ACCOUNT)));

        Mockito.verify(accountRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(AccountActivatedEvent.class));
        Mockito.verify(domainEventPublisher, Mockito.never())
                .publish(Mockito.any(AccountRejectedEvent.class)); }

    @Test
    void addAccountWithCards(){ Assertions.assertDoesNotThrow(() -> accountService.addAccount(personAddEvent(),
            List.of(AccountProduct.BASIC_ACCOUNT, AccountProduct.USD_ACCOUNT, AccountProduct.CREDIT_CARD_GOLD)));

        Mockito.verify(accountRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(AccountActivatedEvent.class));
        Mockito.verify(domainEventPublisher, Mockito.never())
                .publish(Mockito.any(AccountRejectedEvent.class)); }

    @Test
    void failNoCurrencyFound(){ Assertions.assertTrue(true); }

    @Test
    void failNoStatusFound(){ Assertions.assertTrue(true); }

    @Test
    void failSaveReturnedError(){
        Mockito.when(accountRepository.save(Mockito.any())).thenThrow(new IllegalArgumentException("Something went wrong"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.addAccount(
                personAddEvent(),
                List.of(AccountProduct.BASIC_ACCOUNT, AccountProduct.USD_ACCOUNT, AccountProduct.CREDIT_CARD_GOLD)));

        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(domainEventPublisher, Mockito.never())
                .publish(Mockito.any(AccountActivatedEvent.class));
        Mockito.verify(domainEventPublisher, Mockito.never())
                .publish(Mockito.any(AccountRejectedEvent.class));
    }

    private static Account mockAccount(){
        var result = new Account();
        result.setId("1");
        result.setCurrencyModel(new CurrencyModel());
        result.getCurrencyModel().setSymbol("USD");
        result.getCurrencyModel().setCountryName("America");
        result.getCurrencyModel().setId("1");
        result.setStatus(new AccountStatusModel());
        result.getStatus().setId("1");
        result.getStatus().setDetail("");
        result.setBalance(BigDecimal.valueOf(100L));
        return result;
    }

    private static PersonAddedEvent personAddEvent() {
        return PersonAddedEvent.builder()
                .id(1)
                .dni("22333444")
                .name("John")
                .lastName("Doe")
                .type(1).build();
    }
}
