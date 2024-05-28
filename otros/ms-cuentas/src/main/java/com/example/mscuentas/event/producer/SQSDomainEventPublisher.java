package com.example.mscuentas.event.producer;

import com.example.mscuentas.event.catalog.DomainEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQSDomainEventPublisher extends DomainEventPublisher{
    @Override
    protected void handlePublish(DomainEvent domainEvent) {
        log.info("Published event on thread {} : {}",Thread.currentThread().threadId(), domainEvent);
    }
}
