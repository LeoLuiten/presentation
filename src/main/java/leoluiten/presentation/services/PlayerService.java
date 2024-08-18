package leoluiten.presentation.services;

import leoluiten.presentation.dtos.player.PlayerDTO;
import leoluiten.presentation.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing players. Provides methods for
 * retrieving and saving player data.
 */
@Service
public interface PlayerService {

    /**
     * Retrieves a player by their unique ID.
     *
     * @param id the ID of the player to retrieve.
     * @return the {@link Player} corresponding to the provided ID.
     */
    Player getPlayerById(Long id);

    /**
     * Saves a player's data.
     *
     * @param player the {@link Player} object containing the player's data to be saved.
     * @return the saved {@link Player} object.
     */
    Player savePlayer(Player player);

    Player savePlayer(PlayerDTO playerDTO);

    Player getPlayerByUserNameAndPassword(String userName, String password);

    Player getPlayerByEmailAndPassword(String email, String password);

    Player getPlayerByUserNameOrEmailAndPassword(String identity, String password);

}
