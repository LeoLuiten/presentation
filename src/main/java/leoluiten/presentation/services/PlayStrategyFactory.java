package leoluiten.presentation.services;

import leoluiten.presentation.models.rps.MatchRps;
import leoluiten.presentation.models.rps.PlayRps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayStrategyFactory {

    @Autowired
    private PlayMatch<PlayRps, MatchRps> playMatchRps;

    public PlayMatch getPlayStrategy(String gameCode) {
        switch (gameCode) {
            case "RPS":
                return playMatchRps;
            default:
                return playMatchRps;
        }
    }
}
