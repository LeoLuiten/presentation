package leoluiten.presentation.services.impl;

import jakarta.persistence.EntityNotFoundException;
import leoluiten.presentation.dtos.PlayerDTO;
import leoluiten.presentation.entities.PlayerEntity;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.repositories.jpa.PlayerJpaRepository;
import leoluiten.presentation.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
     * Retrieves a player by its ID, converting the entity to a model object.
     *
     * @param id the ID of the player to retrieve.
     * @return the {@link Player} model object corresponding to the retrieved player entity.
     */
    @Override
    public Player getPlayerById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        if(Objects.isNull(playerEntity.getUserName())) {
            throw new EntityNotFoundException();
        } else {
            return modelMapper.map(playerEntity, Player.class);
        }
    }

    /**
     * Saves a new player to the database if a player with the same username or email does not already exist.
     * The method converts the provided {@link Player} model object to an entity for persistence,
     * and after saving, converts it back to a {@link Player} model object.
     *
     * If a player with the same username or email already exists, the method returns {@code null}.
     *
     * @param player the {@link Player} model object to be saved.
     * @return the saved {@link Player} model object, or {@code null}
     * if a player with the same username or email already exists.
     */
    @Override
    public Player savePlayer(Player player) {
        return savePlayerInternal(player);
    }

    @Override
    public Player savePlayer(PlayerDTO playerDTO) {
        Player player = modelMapper.map(playerDTO, Player.class); // Convert DTO to Player
        return savePlayerInternal(player);
    }

    private Player savePlayerInternal(Player player) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmail(
                player.getUserName(), player.getEmail()
        );
        if (playerEntityOptional.isEmpty()) {
            PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
            PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity);
            return modelMapper.map(playerEntitySaved, Player.class);
        } else {
            return null;
        }
    }

    @Override
    public Player getPlayerByUserNameAndPassword(String userName, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameAndPassword(userName, password);
        if(playerEntityOptional.isPresent()) {
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Username or password invalid");
        }
    }

    @Override
    public Player getPlayerByEmailAndPassword(String email, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByEmailAndPassword(email, password);
        if(playerEntityOptional.isPresent()) {
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Some credentials are invalid!");
        }
    }

    @Override
    public Player getPlayerByUserNameOrEmailAndPassword(String identity, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmailAndPassword(identity, password);
        if(playerEntityOptional.isPresent()) {
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Some parameters are incorrect!");
        }
    }

}
