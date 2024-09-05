package com.example.mscuentas.service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.dto.GetAccountDto;
import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.enums.AccountStatus;
import com.example.mscuentas.enums.Currency;
import com.example.mspersonas.event.catalog.AccountActivatedEvent;
import com.example.mspersonas.event.catalog.AccountRejectedEvent;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.model.Account;
import com.example.mscuentas.repository.AccountRepository;
import com.example.mscuentas.repository.AccountStatusRepository;
import com.example.mscuentas.repository.CurrencyRepository;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.example.mscuentas.enums.ErrorCode.EV003;

@Service
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final AccountStatusRepository accountStatusRepository;
    private final DomainEventPublisher domainEventPublisher;

    public AccountService(final AccountRepository accountRepository,
                          final DomainEventPublisher domainEventPublisher,
                          final CurrencyRepository currencyRepository,
                          final AccountStatusRepository accountStatusRepository){
        this.accountRepository = accountRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.currencyRepository = currencyRepository;
        this.accountStatusRepository = accountStatusRepository;
    }

    public GetAccountDto getAccount(String id) {
        return this.accountRepository.findById(id).
                map(account -> new GetAccountDto(
                        account.getId(),
                        account.getPersonNumber(),
                        AccountStatus.getByValue(account.getStatus().getId()),
                        Currency.getByValue(account.getCurrencyModel().getId()),
                        account.getBalance()))
                .orElseThrow(() -> new ApiException(EV003, notFoundErrorMsg(id)));
    }

    public void addAccount(final PersonAddedEvent event,
                           final List<AccountProduct> availableProducts) {
        if(availableProducts.isEmpty()){
            log.error("Account rejected.");
            domainEventPublisher.publish(new AccountRejectedEvent());
        } else {
            if(availableProducts.contains(AccountProduct.USD_ACCOUNT)){
                addUsdAccount(event);
            }
            addBasicAccount(event);
            domainEventPublisher.publish(new AccountActivatedEvent());
        }

    }

    private void addBasicAccount(PersonAddedEvent event){
        var currency = currencyRepository.findBySymbol("ARS")
                .orElseThrow(() -> new IllegalArgumentException("No currency found"));
        var status = accountStatusRepository.findByDetail("Activa")
                .orElseThrow(() -> new IllegalArgumentException("No status found"));
        this.accountRepository.save(new Account(
                UUID.randomUUID().toString(),
                Integer.decode(event.getDni()),
                currency,
                status,
                BigDecimal.ZERO));
    }

    private void addUsdAccount(PersonAddedEvent event){
        var currency = currencyRepository.findBySymbol("USD")
                .orElseThrow(() -> new IllegalArgumentException("No currency found"));
        var status = accountStatusRepository.findByDetail("Activa")
                .orElseThrow(() -> new IllegalArgumentException("No status found"));
        this.accountRepository.save(new Account(
                UUID.randomUUID().toString(),
                Integer.decode(event.getDni()),
                currency,
                status,
                BigDecimal.ZERO));
    }

    private String notFoundErrorMsg(String id) {
        return String.format("Account with id %s not found", id);
    }
}
