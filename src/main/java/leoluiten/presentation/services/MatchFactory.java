package leoluiten.presentation.services;

import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.rps.MatchRps;


public class MatchFactory {

    public static Match createMatch(String gameCode) {
        switch (gameCode) {
            case "RPS":
                return new MatchRps();
            default:
                return new MatchRps();
        }
    }
}
