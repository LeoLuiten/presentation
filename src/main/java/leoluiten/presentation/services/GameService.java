package leoluiten.presentation.services;

import leoluiten.presentation.models.Game;
import leoluiten.presentation.models.Player;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing games. Provides methods for
 * retrieving games data.
 */
@Service
public interface GameService {

    /**
     * Retrieves games  by their unique ID.
     *
     * @param gameId the ID of the game to retrieve.
     * @return the {@link Game} corresponding to the provided ID.
     */
    Game getGame(Long gameId);
}
