package com.example.mscuentas.event.consumer;

import com.example.mscuentas.event.catalog.AccountActivatedEvent;
import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DomainEventConsumer {
    private final Logger logger = LoggerFactory.getLogger(DomainEventConsumer.class);
    private final AccountService service;
    private final String topics;

    public DomainEventConsumer(final AccountService service,
                               @Value("${personas.event.consumerTopic}") String consumerTopics){
        this.service = service;
        this.topics = consumerTopics;
    }

    /**
     * With Async. It runs on virtual threads.
     * With multi-threading, we lose order.
     * If we care about order and multi-thread, we need lock entities while processing them.
     * Java 21 feature: Type pattern Matching
     * @param event
     */
    @Async("virtualThreadExecutor")
    @KafkaListener(topics = "publication-events")
    public void processMessage(DomainEvent event)  {
        logger.info("received event = {} - {} ", event, event.hashCode());
        switch(event){
            case PersonAddedEvent personAddedEvent -> logger.info("event was PersonAddedEvent: {}", personAddedEvent);
            case AccountActivatedEvent personAddedEvent -> logger.info("event was AccountActivatedEveny: {}", personAddedEvent);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        }
        logger.info("processed event[{}] successfully", event.hashCode());
    }
}
