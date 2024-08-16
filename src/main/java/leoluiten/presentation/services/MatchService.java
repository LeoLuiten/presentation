package leoluiten.presentation.services;

import leoluiten.presentation.models.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    List<Match> getPlayerMatches(Long playerId);
}
