package leoluiten.presentation.repositories.jpa;

import leoluiten.presentation.entities.MatchEntity;
import leoluiten.presentation.entities.MatchRpsEntity;
import leoluiten.presentation.models.Match;

public class MatchEntityFactory {

    public static Class<? extends MatchEntity> getTypeOfMatch(Match match) {
        switch (match.getGame().getCode()){
            case "RPS":
                return MatchRpsEntity.class;
            default:
                return MatchRpsEntity.class;
        }
    }
}
