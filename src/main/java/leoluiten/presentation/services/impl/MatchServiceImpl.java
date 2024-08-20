package leoluiten.presentation.services.impl;

import jakarta.persistence.EntityNotFoundException;
import leoluiten.presentation.dtos.MatchDTO;
import leoluiten.presentation.entities.MatchEntity;
import leoluiten.presentation.models.Game;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.MatchStatus;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.repositories.jpa.MatchJpaRepository;
import leoluiten.presentation.services.GameService;
import leoluiten.presentation.services.MatchFactory;
import leoluiten.presentation.services.MatchService;
import leoluiten.presentation.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GameService gameService;

    /**
     * Retrieves a list of {@link Match} objects associated with a specific player.
     * <p>
     * The {@link Match} class is abstract, and the specific subclass of {@link Match}
     * is determined by the {@link Game} associated with each match. This method uses a
     * Factory Pattern, implemented via {@link MatchFactory}, to support multiple games.
     * As a result, the correct subclass of {@link Match} is instantiated based on the game type.
     * </p>
     *
     * @param playerId the ID of the player whose matches are to be retrieved.
     * @return a list of {@link Match} instances representing the player's matches.
     */
    @Override
    public List<Match> getMatchesByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();
        Optional<List<MatchEntity>> optionalMatchEntities = matchJpaRepository.getAllByPlayerId(playerId);
        optionalMatchEntities.ifPresent(matchEntities -> matchEntities.forEach(
                me -> {
                    matches.add(modelMapper.map(me, MatchFactory.createMatch(me.getGame().getCode()).getClass()));
                }
        ));
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
     *   <li>Create a new {@link Match} instance using {@link MatchFactory#createMatch(String)} based on the game code.</li>
     *   <li>Set the player, game, creation time, and status for the new match.</li>
     *   <li>Save the new match to the database by mapping it to a {@link MatchEntity} and using {@link MatchJpaRepository#save(Object)}.</li>
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
        Match match = MatchFactory.createMatch(game.getCode());
        match.setPlayer(player);
        match.setGame(game);
        match.setCreatedAt(LocalDateTime.now());
        match.setStatus(MatchStatus.STARTED);
        MatchEntity matchEntitySaved = matchJpaRepository.save(
                modelMapper.map(match, MatchEntity.class));
        return modelMapper.map(matchEntitySaved, Match.class);

    }

    /**
     * Retrieves a match by its ID, converting the entity to a model object.
     *
     * @param id the ID of the match to retrieve.
     * @return the {@link Match} model object corresponding to the retrieved match entity.
     */
    @Override
    public Match getMatchById(Long id) {
        MatchEntity matchEntity = matchJpaRepository.getReferenceById(id);
        if(Objects.isNull(matchEntity.getGame())) {
            throw new EntityNotFoundException();
        } else {
            return modelMapper.map(matchEntity, Match.class);
        }
    }
}
