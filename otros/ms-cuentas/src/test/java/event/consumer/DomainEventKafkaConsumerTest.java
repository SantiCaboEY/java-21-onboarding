package event.consumer;

import com.example.mscuentas.event.catalog.AccountActivatedEvent;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.event.consumer.impl.DomainEventKafkaConsumer;
import com.example.mscuentas.event.consumer.handler.PersonAddedEventHandler;
import com.example.mscuentas.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DomainEventKafkaConsumerTest {

    private DomainEventKafkaConsumer domainEventKafkaConsumer;
    private AccountService service;
    private PersonAddedEventHandler personHandler;

    @BeforeEach
    void initialize(){
        this.service = Mockito.mock(AccountService.class);
        this.personHandler = Mockito.mock(PersonAddedEventHandler.class);
        this.domainEventKafkaConsumer = new DomainEventKafkaConsumer(this.service, this.personHandler);
    }

    @Test
    void ok(){
        Assertions.assertDoesNotThrow(() -> domainEventKafkaConsumer.processMessage(new PersonAddedEvent()));
    }

    @Test
    void noHandlerRegistered(){
        Assertions.assertDoesNotThrow(() -> domainEventKafkaConsumer.processMessage(new AccountActivatedEvent()));
    }
}
