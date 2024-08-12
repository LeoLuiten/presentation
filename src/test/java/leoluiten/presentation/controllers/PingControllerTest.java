package leoluiten.presentation.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test for the PingController, verifying that the `/ping` endpoint
 * returns a "pong" response with an HTTP 200 status.
 * The class is annotated with {@code @SpringBootTest}, indicating that it loads the full application
 * context for integration testing. Additionally, it uses {@code @AutoConfigureMockMvc} to configure
 * the {@link MockMvc} instance, which allows for testing of Spring MVC controllers by simulating HTTP
 * requests and responses.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void pong() throws Exception{
        this.mockMvc.perform(get("/ping")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("pong")));

    }
}