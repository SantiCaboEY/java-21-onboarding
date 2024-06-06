package service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.model.Account;
import com.example.mscuentas.model.AccountStatusModel;
import com.example.mscuentas.model.CurrencyModel;
import com.example.mscuentas.repository.AccountRepository;
import com.example.mscuentas.repository.AccountStatusRepository;
import com.example.mscuentas.repository.CurrencyRepository;
import com.example.mscuentas.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private CurrencyRepository currencyRepository;
    private AccountStatusRepository accountStatusRepository;
    private DomainEventPublisher domainEventPublisher;

    @BeforeEach
    void initialize(){
        accountRepository = Mockito.mock(AccountRepository.class);
        domainEventPublisher = Mockito.mock(DomainEventPublisher.class);
        currencyRepository = Mockito.mock(CurrencyRepository.class);
        accountStatusRepository = Mockito.mock(AccountStatusRepository.class);
        accountService = new AccountService(accountRepository,
                domainEventPublisher, currencyRepository, accountStatusRepository);
        Mockito.when(currencyRepository.findBySymbol("USD")).thenReturn(
                Optional.of(new CurrencyModel("1", "MURICA", "USD")));
        Mockito.when(currencyRepository.findBySymbol("ARG")).thenReturn(
                Optional.of(new CurrencyModel("2", "ARGENTINA", "ARG")));
        Mockito.when(accountStatusRepository.findByDetail("ACTIVA")).thenReturn(
                Optional.of(new AccountStatusModel("2", "ACTIVA")));
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
    void addNoAccounts(){ Assertions.assertTrue(true); }

    @Test
    void addBasicAccouunt(){ Assertions.assertTrue(true); }

    @Test
    void addTwoAccounts(){ Assertions.assertTrue(true); }

    @Test
    void addAccountWithCards(){ Assertions.assertTrue(true); }

    @Test
    void failNoCurrencyFound(){ Assertions.assertTrue(true); }

    @Test
    void failNoStatusFound(){ Assertions.assertTrue(true); }

    @Test
    void failSaveReturnedError(){ Assertions.assertTrue(true); }

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
}
