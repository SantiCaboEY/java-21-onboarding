package com.example.mscuentas.service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.dto.CreateAccountDto;
import com.example.mscuentas.dto.CreateAccountResponseDto;
import com.example.mscuentas.dto.GetAccountDto;
import com.example.mscuentas.enums.AccountStatus;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.model.Account;
import com.example.mscuentas.repository.AccountRepository;
import org.springframework.stereotype.Service;

import static com.example.mscuentas.enums.ErrorCode.EV001;
import static com.example.mscuentas.enums.ErrorCode.EV003;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final DomainEventPublisher domainEventPublisher;

    public AccountService(final AccountRepository accountRepository,
                          final DomainEventPublisher domainEventPublisher){
        this.accountRepository = accountRepository;
        this.domainEventPublisher = domainEventPublisher;
    }
    public CreateAccountResponseDto addPerson(final CreateAccountDto createAccountDto){
        var existingRecord = accountRepository.findByDni(createAccountDto.dni());
        var  errorMsg = existingRecord.map(Account::getStatus).map(AccountStatus::getCreateError)
                .orElse("");
        if(!errorMsg.isBlank()) throw new ApiException(EV001, errorMsg);

        var newPerson = existingRecord.map(p -> {p.setStatus(AccountStatus.INACTIVO); return p;})
                .orElseGet(() -> Account.builder()
                        .dni(createAccountDto.dni())
                        .type(createAccountDto.type())
                        .name(createAccountDto.name())
                        .lastName(createAccountDto.lastName())
                        .status(AccountStatus.INACTIVO)
                        .build()
        );

        var result = accountRepository.save(newPerson);
        domainEventPublisher.publish(PersonAddedEvent.builder()
                .type(result.getType())
                .name(result.getName())
                .lastName(result.getLastName())
                .id(result.getId())
                .dni(result.getDni())
                .build());
        return new CreateAccountResponseDto(result.getId().toString());
    }

    public GetAccountDto getPerson(String id) {
        return this.accountRepository.findById(id).
                map(account -> new GetAccountDto(
                        account.getId(),
                        account.getName(),
                        account.getLastName(),
                        account.getDni(),
                        account.getStatus(),
                        account. getType()))
                .orElseThrow(() -> new ApiException(EV003, notFoundErrorMsg(id)));
    }

    private String notFoundErrorMsg(String id) {
        return String.format("Person with id %s not found", id);
    }
}
