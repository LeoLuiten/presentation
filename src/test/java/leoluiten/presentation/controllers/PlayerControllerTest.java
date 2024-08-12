package leoluiten.presentation.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.PlayerService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
     * MockBean annotation is used to add mock objects to the Spring application context.
     * Here, we are mocking the PlayerService, which is a dependency of the PlayerController.
     */
    @MockBean
    private PlayerService playerService;

    /**
     * This test case is designed to test the `getById` method of the PlayerController.
     * It creates a mock Player object and mocks the PlayerService's `getPlayerById` method to return this player.
     * Then, it performs a GET request to "/players/1" and expects the response status to be OK (200).
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
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk());
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