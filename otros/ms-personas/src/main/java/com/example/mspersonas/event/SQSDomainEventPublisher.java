package com.example.mspersonas.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class SQSDomainEventPublisher extends DomainEventPublisher{
    @Override
    protected void handlePublish(DomainEvent domainEvent) {
        log.info("Published event on thread {} : {}",Thread.currentThread().threadId(), domainEvent);
    }
}
