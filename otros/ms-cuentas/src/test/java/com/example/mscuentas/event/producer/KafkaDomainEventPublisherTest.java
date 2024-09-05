package com.example.mscuentas.event.producer;

import com.example.mscuentas.event.producer.impl.KafkaDomainEventPublisher;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaDomainEventPublisherTest {

    private KafkaDomainEventPublisher publisher;

    @BeforeEach
    void intialize(){
        var template = Mockito.mock(KafkaTemplate.class);
        publisher= new KafkaDomainEventPublisher(template, "personas");

    }
    @Test
    void ok(){
        Assertions.assertDoesNotThrow(() -> publisher.publish(PersonAddedEvent.builder().build()));
    }

}
