package com.example.mscuentas.service;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.dto.GetCardDto;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.repository.CardRepository;
import com.example.mspersonas.event.catalog.AccountActivatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardService {

    private final DomainEventPublisher domainEventPublisher;
    private final CardRepository cardRepository;

    public CardService(final DomainEventPublisher domainEventPublisher,
                       final CardRepository cardRepository) {
        this.domainEventPublisher = domainEventPublisher;
        this.cardRepository = cardRepository;
    }


    public GetCardDto getCard(String id) {
        /** logic **/
        var card = cardRepository.findById(id).orElseThrow(() -> new ApiException());
        return new GetCardDto(card.getNumber());
    }

    public void addAccount(final AccountActivatedEvent event) {
        /** LOGIC **/
    }
}
