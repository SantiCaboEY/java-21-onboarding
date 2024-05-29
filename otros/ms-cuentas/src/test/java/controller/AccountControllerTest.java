package controller;

import com.example.mscuentas.MsCuentasApplication;
import com.example.mscuentas.dto.CreateAccountDto;
import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = {MsCuentasApplication.class})
@AutoConfigureMockMvc
@DirtiesContext
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DomainEventPublisher kafkaDomainEventPublisher;


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
        mockMvc.perform(get("/account/1"))
                .andExpect(status().is4xxClientError());
    }
}
