package leoluiten.presentation.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import leoluiten.presentation.dtos.MatchDTO;
import leoluiten.presentation.dtos.play.PlayRequest;
import leoluiten.presentation.entities.MatchEntity;
import leoluiten.presentation.models.*;
import leoluiten.presentation.repositories.jpa.MatchEntityFactory;
import leoluiten.presentation.repositories.jpa.MatchJpaRepository;
import leoluiten.presentation.services.*;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MatchServiceImpl implements MatchService {

    private static final Long APP_PLAYER_ID = 1000000L;

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayStrategyFactory playStrategyFactory;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves a list of {@link Match} objects associated with a specific player.
     * <p>
     * The {@link Match} class is abstract, and the specific subclass of {@link Match}
     * is determined by the {@link Game} associated with each match. This method uses a
     * Factory Pattern, implemented via {@link MatchFactory}, to create instances of the
     * appropriate subclass of {@link Match} based on the game type.
     * </p>
     *
     * <p>
     * The method fetches all matches where the specified player is either the first player
     * (player1) or the second player (player2) from the data source using the {@link MatchJpaRepository}.
     * Each {@link MatchEntity} is then converted into the correct {@link Match} subclass using
     * the {@link MatchFactory} and added to the returned list.
     * </p>
     *
     * @param playerId the ID of the player whose matches are to be retrieved.
     * @return a list of {@link Match} instances representing the matches involving the specified player.
     */
    @Override
    public List<Match> getMatchesByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();
        List<MatchEntity> matchEntities = matchJpaRepository.getAllByPlayerId(playerId);
        matchEntities.forEach(me -> {
            matches.add(modelMapper.map(me, MatchFactory.getTypeOfMatch(me.getGame().getCode())));
        });

        return matches;
    }


    /**
     * Creates a new {@link Match} based on the provided {@link MatchDTO}.
     * <p>
     * This method initializes a new match using the provided player and game IDs from the {@code MatchDTO}.
     * The specific type of {@code Match} is determined by the game type, using the Factory Pattern
     * implemented in {@link MatchFactory}. The method then sets the match's player, game, creation time,
     * and initial status before saving it to the database.
     * </p>
     *
     * <p>Process Overview:</p>
     * <ol>
     *   <li>Retrieve the {@link Player} by their ID using {@link PlayerService#getPlayerById(Long)}.</li>
     *   <li>Retrieve the {@link Game} by its ID using {@link GameService#getGame(Long)}.</li>
     *   <li>Create a new {@link Match} instance using {@link MatchFactory#createMatch(Player, Game)} based on the game code.</li>
     *   <li>Save the new match to the database by mapping it to a class that extends {@link MatchEntity} and using {@link MatchJpaRepository#save(Object)}.</li>
     *   <li>Return the saved match as a {@link Match} object.</li>
     * </ol>
     *
     * @param matchDTO the {@link MatchDTO} containing the data required to create a new match.
     * @return the created {@link Match} instance, after it has been saved to the database.
     * @throws EntityNotFoundException if the player ID provided does not correspond to an existing player.
     * @throws EntityNotFoundException if the game ID provided does not correspond to an existing game.
     */
    @Override
    public Match createMatch(MatchDTO matchDTO) {
        Player player = playerService.getPlayerById(matchDTO.getPlayerId());
        Game game = gameService.getGame(matchDTO.getGameId());
        Match match = MatchFactory.createMatch(player, game);
        MatchEntity matchEntitySaved = matchJpaRepository.save(
                modelMapper.map(match, MatchEntityFactory.getTypeOfMatch(match))
        );
        return modelMapper.map(matchEntitySaved, match.getClass());

    }

    /**
     * Retrieves a match by its ID, converting the entity to a model object.
     *
     * @param id the ID of the match to retrieve.
     * @return the {@link Match} model object corresponding to the retrieved match entity.
     */
    @Override
    public Match getMatchById(Long id) {
        //INFO: https://www.baeldung.com/hibernate-proxy-to-real-entity-object
        MatchEntity matchEntity = (MatchEntity) Hibernate.unproxy(matchJpaRepository.getReferenceById(id));
        if(Objects.isNull(matchEntity.getGame())) {
            throw new EntityNotFoundException();
        } else {
            return modelMapper.map(matchEntity, MatchFactory.getTypeOfMatch(matchEntity.getGame().getCode()));
        }
    }

    /**
     * Processes a play within an existing match, applying the rules specific to the game's type.
     * Both the match and the play are saved correctly according to the game rules (e.g., Rock-Paper-Scissors, Tic-Tac-Toe).
     *
     * This method performs the following steps:
     * 1. Retrieves the match by its ID.
     * 2. Creates a new Play instance based on the match type and the details provided in the play request.
     * 3. Uses a strategy pattern to determine how to handle the play based on game-specific rules.
     * 4. Executes the play within the match context.
     *
     * @param matchId The ID of the match in which the play is to be made.
     * @param playRequest The details of the play to process, including information specific to the game's rules.
     * @return The result of the play, which includes the updated state of the match.
     * @throws EntityNotFoundException if no match is found with the given match ID.
     */
    @Transactional
    @Override
    public Play play(Long matchId, PlayRequest playRequest) {
        Match match = this.getMatchById(matchId);
        if (match == null) {
            throw new EntityNotFoundException("Match not found with ID: " + matchId);
        }
        if(match.getStatus() != MatchStatus.STARTED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("The match is %s", match.getStatus()));
        }
        // Use a factory to create a Play instance based on the type of match.
        // Since Match is an abstract class, the factory determines the specific game type (e.g., Rock-Paper-Scissors, Tic-Tac-Toe).
        Play play = PlayFactory.getPlayInstance(playRequest, match.getGame().getCode());
        // Retrieve the appropriate play strategy for the game's rules using the PlayStrategyFactory.
        // This determines how to handle the play situation according to the game-specific rules.
        PlayMatch playMatch = playStrategyFactory.getPlayStrategy(match.getGame().getCode());
        // Execute the play using the appropriate strategy and return the result.
        return playMatch.play(play, match);
    }

}
