package leoluiten.presentation.services.impl;

import leoluiten.presentation.entities.PlayerEntity;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.repositories.jpa.PlayerJpaRepository;
import leoluiten.presentation.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link PlayerService} interface. This service
 * provides methods for retrieving and saving player information.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves a player by their ID, converting the entity to a model object.
     *
     * @param id the ID of the player to retrieve.
     * @return the {@link Player} model object corresponding to the retrieved player entity.
     */
    @Override
    public Player getPlayerById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        return modelMapper.map(playerEntity, Player.class);
    }

    /**
     * Saves a player, converting the model object to an entity for persistence,
     * and then converting it back to a model object after saving.
     *
     * @param player the {@link Player} model object to be saved.
     * @return the saved {@link Player} model object.
     */
    @Override
    public Player savePlayer(Player player) {
        PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
        PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity);
        return modelMapper.map(playerEntitySaved, Player.class);
    }
}
