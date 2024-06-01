package com.example.mscuentas.event.consumer.handler;

import com.example.mscuentas.event.catalog.DomainEvent;

public sealed interface DomainEventHandler<T extends DomainEvent> permits PersonAddedEventHandler {
     void handle(final T event);
}
