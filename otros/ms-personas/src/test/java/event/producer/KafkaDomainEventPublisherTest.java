package event.producer;

import com.example.mspersonas.config.KafkaEventsProperties;
import com.example.mspersonas.event.catalog.DomainEvent;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import com.example.mspersonas.event.producer.KafkaDomainEventPublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/*@SpringBootTest(classes = {
        KafkaDomainEventPublisher.class,
        KafkaConfiguration.class,
        KafkaEventsProperties.class,
        MultiThreadingConfiguration.class})*/

public class KafkaDomainEventPublisherTest {

    private KafkaDomainEventPublisher publisher;

    private final KafkaTemplate<Integer, DomainEvent> template = Mockito.mock(KafkaTemplate.class);

    @BeforeEach
    void initialize(){
        publisher = new KafkaDomainEventPublisher(template,new KafkaEventsProperties("",""));
    }

    @Test
    void ok(){
        Assertions.assertDoesNotThrow(() -> publisher.publish(PersonAddedEvent.builder().build()));
    }

}
