package com.example.mscuentas;

import com.example.mspersonas.event.catalog.PersonAddedEvent;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


@SpringBootTest
@WireMockTest
@EmbeddedKafka(topics = {"cuentas", "personas", "tarjetas"})
@Slf4j
@DirtiesContext
public class MsTarjetasApplicationTest {

    @Autowired
    private KafkaTemplate<String, PersonAddedEvent> kafkaTemplate;

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
            .options(wireMockConfig().port(3337).dynamicHttpsPort())
            .build();

    private final static String DNI = "22333444";

    private final static String OK_RENAPER = """
            {
                dni:12345678,
                isAuthorize:true
            }
            """;
    private final static String OK_WORLDSYS = """
            {
                dni:12345678,
                isTerrorist:true
            }
            """;

    private final static String OK_VERAZ = """
            {
                dni:12345678,
                score:99
            }
            """;

    @Test
    void ok() throws InterruptedException {
        /*wm1.stubFor(WireMock.get("/service/renaper/" + DNI)
                .willReturn(ResponseDefinitionBuilder.okForJson(new GetAuthorizationStatusResponseDto(1, true))));
        wm1.stubFor(WireMock.get("/service/veraz/" + DNI)
                .willReturn(ResponseDefinitionBuilder.okForJson(new GetScoreResponseDto(1, 99.0f))));
        wm1.stubFor(WireMock.get("/service/worldsys/" + DNI)
                .willReturn(ResponseDefinitionBuilder.okForJson(new GetAntiTerrorismStatusResponseDto(1, false))));


        kafkaTemplate.send(new ProducerRecord<>("personas", personAddEvent()));
        Thread.sleep(10000);*/

        //Esperar recibir un AccountActivatedEvent
    }
}
