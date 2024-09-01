package leoluiten.presentation.services;

import leoluiten.presentation.dtos.MatchDTO;
import leoluiten.presentation.dtos.play.PlayRequest;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.Play;
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

    /**
     * Retrieves a match object along the information of the game and player associated to it.
     *
     * @param id the id of the match to retrieve.
     * @return a Match object containing information of a Match and its related game and player
     */
    Match getMatchById (Long id);

    /**
     * This method serves the purpose of abstracting the specific game from which a play is pretending to be made
     * @param matchId the id of the match that involves the play to make
     * @param play the play request to process (for example on a RPS play request, that specific game rules would come into place)
     * @return Play the processed play persisted.
     */
    Play play(Long matchId, PlayRequest play);
}
