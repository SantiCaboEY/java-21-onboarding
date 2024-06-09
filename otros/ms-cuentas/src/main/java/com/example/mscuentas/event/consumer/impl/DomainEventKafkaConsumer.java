package com.example.mscuentas.event.consumer.impl;

import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.event.consumer.DomainEventConsumer;
import com.example.mscuentas.event.consumer.handler.DomainEventHandler;
import com.example.mscuentas.event.consumer.handler.PersonAddedEventHandler;
import com.example.mscuentas.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class DomainEventKafkaConsumer implements DomainEventConsumer {
    private final Logger logger = LoggerFactory.getLogger(DomainEventKafkaConsumer.class);
    private final AccountService service;
    private final Map<Class<? extends  DomainEvent>, DomainEventHandler> handlerMap;

    public DomainEventKafkaConsumer(final AccountService service,
                                    final PersonAddedEventHandler personAddedEventHandler){
        this.service = service;
        handlerMap = new HashMap<>();
        handlerMap.put(PersonAddedEvent.class, personAddedEventHandler);
    }

    /**
     * With Async. It runs on virtual threads.
     * With multi-threading, we lose order.
     * If we care about order and multi-thread, we need lock entities while processing them.
     * Java 21 feature: Type pattern Matching
     * @param event
     */
    @Async("virtualThreadExecutor")
    @KafkaListener(topics = {"personas", "tarjetas"})
    public void processMessage(final DomainEvent event)  {
        logger.info("received event = {} - {} ", event, event.hashCode());
        var handler = handlerMap.get(event.getClass());
        if(Objects.isNull(handler)) {
            logger.error("No handler registered for {} event. Ignoring event", event.getEventName());
        } else {
            handler.handle(event);
            logger.info("processed event[{}] successfully", event.hashCode());
        }

    }
/*
    @Async("virtualThreadExecutor")
    @KafkaListener(topics = {"personas"})
    public void processMessage(final PersonAddedEvent event) {
        logger.info("received event = {} - {} ", event, event.hashCode());
    }*/
}
