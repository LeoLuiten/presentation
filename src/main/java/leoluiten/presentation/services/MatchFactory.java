package leoluiten.presentation.services;

import leoluiten.presentation.models.Game;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.MatchStatus;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.models.rps.MatchRps;
import leoluiten.presentation.models.rps.PlayRps;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class MatchFactory {

    public static Match createMatch(Player player, Game game) {
        switch (game.getCode()) {
            case "RPS":
                return createMatchRps(player, game);
            default:
                return createMatchRps(player, game);
        }
    }

    public static Class<? extends Match> getTypeOfMatch(String gamecode) {
        switch (gamecode) {
            case "RPS":
                return MatchRps.class;
            default:
                return MatchRps.class;
        }
    }

    public static Match createMatchRps(Player player, Game game) {
        MatchRps match = (MatchRps) getBasicMatch(player, game);
        match.setNumberOfPlays(10);
        match.setRemainingPlays(10);
        match.setPlayer1Score(0);
        match.setPlayer2Score(0);
        match.setPlays(new ArrayList<PlayRps>());
        return match;
    }

    private static Match getBasicMatch(Player player, Game game) {
        Match match = getMatchInstance(game.getCode());
        match.setPlayer1(player);
        match.setGame(game);
        match.setCreatedAt(LocalDateTime.now());
        match.setStatus(MatchStatus.STARTED);
        return match;
    }

    private static Match getMatchInstance(String gameCode) {
        switch (gameCode) {
            case "RPS":
                return new MatchRps();
            default:
                return new MatchRps();
        }
    }
}
