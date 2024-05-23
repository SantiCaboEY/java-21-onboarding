package com.example.mspersonas.event;

import org.springframework.scheduling.annotation.Async;

public abstract class DomainEventPublisher {
    @Async("virtualThreadExecutor")
    public void publish(final DomainEvent domainEvent){
        handlePublish(domainEvent);
    }

    protected abstract void handlePublish(final DomainEvent domainEvent);

}
