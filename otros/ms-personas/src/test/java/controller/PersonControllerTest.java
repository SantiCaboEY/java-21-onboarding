package controller;

import com.example.mspersonas.MsPersonasApplication;
import com.example.mspersonas.dto.CreatePersonDto;
import com.example.mspersonas.event.catalog.DomainEvent;
import com.example.mspersonas.event.producer.DomainEventPublisher;
import com.example.mspersonas.event.producer.KafkaDomainEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = {MsPersonasApplication.class})
@AutoConfigureMockMvc
@DirtiesContext
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DomainEventPublisher kafkaDomainEventPublisher;

    private static final String NOT_FOUND_RESPONSE = "{\n" +
            "    \"errorCode\": \"EV003\",\n" +
            "    \"description\": \"Person not found\",\n" +
            "    \"errors\": [\n" +
            "        \"Person with id 1 not found\"\n" +
            "    ]\n" +
            "}";

    private static final String FOUND_RESPONSE = "{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"John\",\n" +
            "    \"lastName\": \"Doe\",\n" +
            "    \"dni\": \"11222333\",\n" +
            "    \"status\": \"INACTIVO\",\n" +
            "    \"type\": 1\n" +
            "}";

    @Test
    void personAddSucceed() throws Exception {
        mockMvc.perform(get("/person/1")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(NOT_FOUND_RESPONSE));

        mockMvc.perform(post("/person/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new CreatePersonDto(1, "John","Doe", "11222333"))))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\"}"));

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(FOUND_RESPONSE));

        Mockito.verify(kafkaDomainEventPublisher, Mockito.times(1)).publish(Mockito.any());
    }
}
