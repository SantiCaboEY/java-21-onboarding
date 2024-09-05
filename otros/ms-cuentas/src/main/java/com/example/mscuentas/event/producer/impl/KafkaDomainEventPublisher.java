package com.example.mscuentas.event.producer.impl;

import com.example.mspersonas.event.catalog.DomainEvent;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
public class KafkaDomainEventPublisher extends DomainEventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final String topic;

    @Autowired
    public KafkaDomainEventPublisher(final KafkaTemplate<String, DomainEvent> kafkaTemplate,
                                     @Value("${cuentas.event.producerTopic}") String producerTopic){
        super();
        Assert.notNull(producerTopic, "Kafka properties cannot have topic=null");
        this.kafkaTemplate = kafkaTemplate;
        this.topic = producerTopic;
    }

    //Si esto no funciona en la version generica, voy a tener que armar el DomainEvent con composition.
    @Override
    protected <T extends DomainEvent> void handlePublish(T domainEvent) {

        log.info("Producing event {} : {}", domainEvent.getEventName(), domainEvent.toString());
        kafkaTemplate.send(topic, domainEvent);
        log.info("Produced event {}", domainEvent.getEventName());
    }
}
