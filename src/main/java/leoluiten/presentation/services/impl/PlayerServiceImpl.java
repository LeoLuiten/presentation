package leoluiten.presentation.services.impl;

import leoluiten.presentation.entities.PlayerEntity;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.repositories.jpa.PlayerJpaRepository;
import leoluiten.presentation.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Player getPlayerById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        return modelMapper.map(playerEntity, Player.class);
    }
}
