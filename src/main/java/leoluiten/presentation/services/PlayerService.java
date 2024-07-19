package leoluiten.presentation.services;

import leoluiten.presentation.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

    Player getPlayerById(Long id);
}
