package leoluiten.presentation;


import leoluiten.presentation.controllers.PingController;
import leoluiten.presentation.controllers.PlayerController;
import leoluiten.presentation.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test class verifies that the controller and service beans
 * are correctly loaded into the application context.
 */
@SpringBootTest
public class SmokeTest {

    @Autowired
    private PlayerController playerController;

    @Autowired
    private PingController pingController;

    @Autowired
    private PlayerService playerService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
        assertThat(pingController).isNotNull();
        assertThat(playerService).isNotNull();
    }
}
