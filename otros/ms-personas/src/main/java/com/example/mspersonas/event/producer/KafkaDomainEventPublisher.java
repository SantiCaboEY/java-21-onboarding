package com.example.mspersonas.event.producer;

import com.example.mspersonas.config.KafkaEventsProperties;
import com.example.mspersonas.event.catalog.DomainEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaDomainEventPublisher extends DomainEventPublisher{
    private final KafkaTemplate<Integer, DomainEvent> kafkaTemplate;
    private final String topic;

    public KafkaDomainEventPublisher(final KafkaTemplate<Integer, DomainEvent> kafkaTemplate,
                                     final KafkaEventsProperties kafkaEventsProperties){
        super();
        this.kafkaTemplate = kafkaTemplate;
        this.topic = kafkaEventsProperties.getProducerTopic();
    }

    @Override
    protected <T extends DomainEvent> void handlePublish(T domainEvent) {
        kafkaTemplate.send(topic, domainEvent);
    }
}
