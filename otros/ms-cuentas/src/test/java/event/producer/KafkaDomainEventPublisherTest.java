package event.producer;

import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.event.producer.KafkaDomainEventPublisher;
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
