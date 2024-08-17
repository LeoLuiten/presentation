package leoluiten.presentation.services.impl;

import leoluiten.presentation.entities.GameEntity;
import leoluiten.presentation.models.Game;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.repositories.jpa.GameJpaRepository;
import leoluiten.presentation.services.GameService;
import leoluiten.presentation.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link GameService} interface. This service
 * provides methods for retrieving and saving game information.
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves a game by its ID, converting the entity to a model object.
     *
     * @param gameId the ID of the game to retrieve.
     * @return the {@link Player} model object corresponding to the retrieved game entity.
     */
    @Override
    public Game getGame(Long gameId) {
        GameEntity gameEntity = gameJpaRepository.getReferenceById(gameId);
        return modelMapper.map(gameEntity, Game.class);
    }
}
