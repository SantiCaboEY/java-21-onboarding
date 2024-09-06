package com.example.mscuentas;

import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.repository.CardRepository;
import com.example.mspersonas.event.catalog.AccountActivatedEvent;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


@SpringBootTest
@WireMockTest
@EmbeddedKafka(topics = {"cuentas", "personas", "tarjetas"})
@Slf4j
@DirtiesContext
public class MsTarjetasApplicationTest {

    @Autowired
    private KafkaTemplate<String, AccountActivatedEvent> kafkaTemplate;

    @Autowired
    private CardRepository cardRepository;

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
            .options(wireMockConfig().port(3337).dynamicHttpsPort())
            .build();

    private final static String DNI = "22333444";

    @Test
    void ok() throws InterruptedException {
        kafkaTemplate.send(new ProducerRecord<>("cuentas", accountActivatedEvent()));

        Thread.sleep(5000);

        //We should expect two more entries here
        var results = cardRepository.findAll().iterator();
        int i= 0;
        while(results.hasNext()){
            i++;
            results.next();
        }
        Assertions.assertEquals(7, i);
    }

    private AccountActivatedEvent accountActivatedEvent() {
        var event =  AccountActivatedEvent.builder().activatedProducts(
                        List.of(AccountProduct.CREDIT_CARD_BASIC, AccountProduct.CREDIT_CARD_PLATINUM))
                .personDni(DNI)
                .accountId("123456")
                .personName("Santi")
                .personLastName("Doe")
                .build();
        event.setEventName("person.account.activated");
        return event;
    }
}
