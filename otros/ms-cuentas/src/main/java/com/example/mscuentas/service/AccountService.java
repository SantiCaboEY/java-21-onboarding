package com.example.mscuentas.service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.dto.GetAccountDto;
import com.example.mscuentas.enums.AccountStatus;
import com.example.mscuentas.enums.SymbolMoney;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.repository.AccountRepository;
import org.springframework.stereotype.Service;

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

    public GetAccountDto getAccount(String id) {
        return this.accountRepository.findById(id).
                map(account -> new GetAccountDto(
                        account.getId(),
                        account.getPersonNumber(),
                        AccountStatus.getByValue(account.getStatus().getId()),
                        SymbolMoney.getByValue(account.getMoneySymbol().getId()),
                        account.getBalance()))
                .orElseThrow(() -> new ApiException(EV003, notFoundErrorMsg(id)));
    }

    private String notFoundErrorMsg(String id) {
        return String.format("Account with id %s not found", id);
    }
}
