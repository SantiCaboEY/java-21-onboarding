package service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.dto.CreateAccountDto;
import com.example.mscuentas.enums.AccountStatus;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.model.Account;
import com.example.mscuentas.repository.AccountRepository;
import com.example.mscuentas.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;

import java.util.Optional;

class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private DomainEventPublisher domainEventPublisher;
    private CreateAccountDto personDto;

    @BeforeEach
    void initialize(){
        accountRepository = Mockito.mock(AccountRepository.class);
        domainEventPublisher = Mockito.mock(DomainEventPublisher.class);
        accountService = new AccountService(accountRepository, domainEventPublisher);
        personDto = new CreateAccountDto(1, "John","Doe", "11222333");
    }
    @Test
    void givenNonExistingPersonSucceed(){
        Mockito.when(accountRepository.findByDni(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(person(AccountStatus.CANCELADO)));
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(person());

        var result = accountService.addPerson(personDto);

        Assertions.assertEquals("1", result.id());

        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(PersonAddedEvent.class));

    }

    @Test
    void givenExistingPersonSucceed(){
        Mockito.when(accountRepository.findByDni(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(person());

        var result = accountService.addPerson(personDto);

        Assertions.assertEquals("1", result.id());
        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(PersonAddedEvent.class));
    }

    @ParameterizedTest()
    @EnumSource(value = AccountStatus.class, names = {"ACTIVO", "INACTIVO", "SUSPENDIDO", "BLOQUEADO"})
    void failInvalidState(AccountStatus invalidStatus){
        Mockito.when(accountRepository.findByDni(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(person(invalidStatus)));

        Assertions.assertThrows(ApiException.class, () -> accountService.addPerson(personDto));
        Mockito.verify(domainEventPublisher, Mockito.never()).publish(Mockito.any(PersonAddedEvent.class));
    }

    private static Account person(){
        return person(AccountStatus.INACTIVO);
    }

    private static Account person(AccountStatus accountStatus){
        return Account.builder()
                .id(1)
                .type(1)
                .dni("11222333")
                .name("John")
                .lastName("Doe")
                .status(accountStatus)
                .build();
    }
}
