package com.example.mscuentas.controller;

import com.example.mscuentas.MsCuentasApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

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

    private static final String FOUND_RESPONSE = """
            {
                "id": "1234567890",
                "personNumber": 1,
                "status": "ACTIVA",
                "moneySymbol": "USD",
                "balance": 10000
            }""";


    @Test
    void personAddSucceed() throws Exception {
        mockMvc.perform(get("/account/1234567890"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(FOUND_RESPONSE));
    }
}
