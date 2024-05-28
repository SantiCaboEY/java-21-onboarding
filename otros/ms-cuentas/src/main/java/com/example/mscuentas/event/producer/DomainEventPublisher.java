package com.example.mscuentas.event.producer;

import com.example.mscuentas.event.catalog.DomainEvent;
import org.springframework.scheduling.annotation.Async;

public abstract class DomainEventPublisher {
    @Async("virtualThreadExecutor")
    public <T extends DomainEvent> void publish(final T domainEvent){
        handlePublish(domainEvent);
    }

    protected abstract <T extends DomainEvent> void handlePublish(final T domainEvent);

}
