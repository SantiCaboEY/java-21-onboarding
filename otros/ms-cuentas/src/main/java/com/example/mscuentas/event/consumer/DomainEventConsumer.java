package com.example.mscuentas.event.consumer;

import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DomainEventConsumer {
    private final Logger logger = LoggerFactory.getLogger(DomainEventConsumer.class);
    private final AccountService service;
    private final Map<Class<? extends  DomainEvent>, DomainEventHandler> handlerMap;

    public DomainEventConsumer(final AccountService service){
        this.service = service;
        handlerMap = new HashMap<>();
        handlerMap.put(PersonAddedEvent.class, new PersonAddedDomainEventHandler());
    }

    /**
     * With Async. It runs on virtual threads.
     * With multi-threading, we lose order.
     * If we care about order and multi-thread, we need lock entities while processing them.
     * Java 21 feature: Type pattern Matching
     * @param event
     */
    @Async("virtualThreadExecutor")
    @KafkaListener(topics = "${personas.event.consumerTopic}")
    public void processMessage(final DomainEvent event)  {
        logger.info("received event = {} - {} ", event, event.hashCode());
        handlerMap.get(event.getClass()).handle(event);
        logger.info("processed event[{}] successfully", event.hashCode());
    }
}
