package com.example.mscuentas.service;

import com.example.mscuentas.dto.GetCardDto;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mspersonas.event.catalog.AccountActivatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardService {

    private final DomainEventPublisher domainEventPublisher;

    public CardService(final DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }


    public GetCardDto getCard(String id) {
        /** logic **/
        return null;
    }

    public void addAccount(final AccountActivatedEvent event) {
        /** LOGIC **/
    }
}
