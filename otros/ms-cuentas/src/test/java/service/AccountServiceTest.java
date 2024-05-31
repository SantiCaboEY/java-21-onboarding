package service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.model.Account;
import com.example.mscuentas.model.AccountStatusModel;
import com.example.mscuentas.model.MoneySymbol;
import com.example.mscuentas.repository.AccountRepository;
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
    private DomainEventPublisher domainEventPublisher;

    @BeforeEach
    void initialize(){
        accountRepository = Mockito.mock(AccountRepository.class);
        domainEventPublisher = Mockito.mock(DomainEventPublisher.class);
        accountService = new AccountService(accountRepository, domainEventPublisher);
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
        Mockito.when(accountRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(mockAccount()));
        var foundAccount =  accountService.getAccount("1");
        Assertions.assertNotNull(foundAccount);
        Assertions.assertEquals(BigDecimal.valueOf(100L), foundAccount.balance());
        Assertions.assertEquals("1", foundAccount.id());
        Mockito.verify(domainEventPublisher, Mockito.never()).publish(Mockito.any());

    }

    private static Account mockAccount(){
        var result = new Account();
        result.setId("1");
        result.setMoneySymbol(new MoneySymbol());
        result.getMoneySymbol().setSymbol("USD");
        result.getMoneySymbol().setCountryName("America");
        result.getMoneySymbol().setId("1");
        result.setStatus(new AccountStatusModel());
        result.getStatus().setId("1");
        result.getStatus().setDetail("");
        result.setBalance(BigDecimal.valueOf(100L));
        return result;
    }
}
