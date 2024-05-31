package com.example.mscuentas.event.consumer;

import com.example.mscuentas.event.catalog.DomainEvent;

public interface DomainEventHandler {
     void handle(final DomainEvent event);
}
