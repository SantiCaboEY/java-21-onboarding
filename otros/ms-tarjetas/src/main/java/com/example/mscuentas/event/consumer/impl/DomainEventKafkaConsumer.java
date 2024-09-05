package com.example.mscuentas.event.consumer.impl;

import com.example.mspersonas.event.catalog.DomainEvent;
import com.example.mscuentas.event.consumer.DomainEventConsumer;
import com.example.mscuentas.event.consumer.handler.DomainEventHandler;
import com.example.mscuentas.event.consumer.handler.AccountAddedEventHandler;
import com.example.mscuentas.service.CardService;
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
    private final CardService service;
    private final Map<String, DomainEventHandler> handlerMap;

    public DomainEventKafkaConsumer(final CardService service,
                                    final AccountAddedEventHandler accountAddedEventHandler) {
        this.service = service;
        handlerMap = new HashMap<>();
        handlerMap.put("person.account.activated", accountAddedEventHandler);
    }

    /**
     * With Async. It runs on virtual threads.
     * With multi-threading, we lose order.
     * If we care about order and multi-thread, we need lock entities while processing them.
     * Java 21 feature: Type pattern Matching
     *
     * @param event
     */
    @Async("virtualThreadExecutor")
    @KafkaListener(topics = {"cuentas"})
    public void processMessage(final DomainEvent event) {
        logger.info("-> received event = {} - {} ", event, event.hashCode());
        var handler = handlerMap.get(event.getEventName());
        if (Objects.isNull(handler)) {
            logger.error("No handler registered for {} event. Ignoring event", event.getEventName());
        } else {
            handler.handle(event);
            logger.info("processed event[{}] successfully", event.hashCode());
        }
    }
}
