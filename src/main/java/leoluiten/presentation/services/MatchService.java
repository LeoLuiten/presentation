package leoluiten.presentation.services;

import leoluiten.presentation.dtos.MatchDTO;
import leoluiten.presentation.models.Match;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing matches. Provides methods for
 * retrieving and saving matches.
 */
@Service
public interface MatchService {

    /**
     * Retrieves matches involving a certain player by the player's ID.
     *
     * @param playerId the ID of the player whose matches are requested to be retrieved.
     * @return {@link List<Match>} related to the provided player's ID.
     */
    List<Match> getMatchesByPlayer(Long playerId);

    /**
     * Retrieves games  by their unique ID.
     *
     * @param match a simpler model that only contains the gameId and playerId values.
     * @return the {@link Match} corresponding to the provided ID.
     */
    Match createMatch (MatchDTO match);
}
