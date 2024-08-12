package leoluiten.presentation.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Unit tests for the PlayerController class.
 * This class is annotated with @WebMvcTest, which is used to test Spring MVC controllers.
 * The @WebMvcTest annotation loads only the web layer beans, like controllers, and does not start the entire Spring context.
 */
@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    /**
     * MockMvc is a main entry point for server-side Spring MVC test support.
     * It allows performing requests and assertions against the response.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * ObjectMapper is used to convert Java objects into JSON and vice versa.
     * In this test class, it is used to deserialize the JSON response content from
     * the MockMvc result into a Player object for further assertions.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * MockBean annotation is used to add mock objects to the Spring application context.
     * Here, we are mocking the PlayerService, which is a dependency of the PlayerController.
     */
    @MockBean
    private PlayerService playerService;

    /**
     * This test case verifies the `getById` method of the PlayerController.
     * It sets up a mock Player object and configures the PlayerService's `getPlayerById` method
     * to return this mock player when called with the ID 1.
     *
     * The test performs a GET request to "/players/1" and verifies that the response:
     * <ul>
     *   <li>Has an HTTP status of OK (200).</li>
     *   <li>Contains the expected JSON properties, including `userName`, `email`, and `password`.</li>
     * </ul>
     *
     * Additionally, the test retrieves the entire response content and deserializes it into a Player object
     * using {@link ObjectMapper} to further assert that the `userName` field is "anUser".
     *
     * @throws Exception if the request fails.
     */
    @Test
    void getByIdTest() throws Exception {
        Player player = new Player();
        player.setId(1l);
        player.setEmail("email@email.com");
        player.setUserName("anUser");
        player.setPassword("Password#03");


        when(playerService.getPlayerById(1l)).thenReturn(player);
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("anUser"))
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andExpect(jsonPath("$.password").value("Password#03"));

        MvcResult mvcResult = this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andReturn();
        Player result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Player.class);

        Assertions.assertEquals("anUser", result.getUserName());

    }

    /**
     * This test case is designed to test the `savePlayer` method of the PlayerController.
     * However, it is currently not implemented.
     * Implementing this test will involve creating a mock Player object and testing the save functionality.
     */
    @Test
    void savePlayer() {
        // TODO: Implement the test case for saving a player
    }
}