package com.example.mspersonas.event.producer;

import com.example.mspersonas.event.catalog.DomainEvent;
import org.springframework.scheduling.annotation.Async;

public abstract class DomainEventPublisher {
    @Async("virtualThreadExecutor")
    public <T extends DomainEvent> void publish(final T domainEvent){
        handlePublish(domainEvent);
    }

    protected abstract <T extends DomainEvent> void handlePublish(final T domainEvent);

}
