package com.example.mscuentas.event.producer.impl;

import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class KafkaDomainEventPublisher extends DomainEventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final String topic;

    @Autowired
    public KafkaDomainEventPublisher(final KafkaTemplate<String, DomainEvent> kafkaTemplate,
                                     @Value("${personas.event.producerTopic}") String producerTopic){
        super();
        Assert.notNull(producerTopic, "Kafka properties cannot have topic=null");
        this.kafkaTemplate = kafkaTemplate;
        this.topic = producerTopic;
    }

    //Si esto no funciona en la version generica, voy a tener que armar el DomainEvent con composition.
    @Override
    protected <T extends DomainEvent> void handlePublish(T domainEvent) {
        kafkaTemplate.send(topic, domainEvent);
    }
}
