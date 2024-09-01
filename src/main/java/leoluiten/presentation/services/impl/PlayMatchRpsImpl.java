package leoluiten.presentation.services.impl;

import leoluiten.presentation.models.MatchStatus;
import leoluiten.presentation.models.rps.HandShape;
import leoluiten.presentation.models.rps.MatchRps;
import leoluiten.presentation.models.rps.PlayRps;
import leoluiten.presentation.services.PlayMatch;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
public class PlayMatchRpsImpl implements PlayMatch<PlayRps, MatchRps> {

    private Random random = new Random();

    @Override
    public PlayRps play(PlayRps playRps, MatchRps matchRps) {
        playRps.setMatchRpsId(matchRps.getId());
        if(Objects.isNull(playRps.getHandShapePlayer2())) {
            playRps.setHandShapePlayer2(getRandomShapeHand());
        }
        evaluatePlay(playRps, matchRps);
        calculateMatchScore(playRps, matchRps);
        calculateMatchStatus(matchRps);
        matchRps.setUpdatedAt(LocalDateTime.now());
        return playRps;
    }

    private HandShape getRandomShapeHand() {
        int randomIndex = random.nextInt(3);
        return HandShape.values()[randomIndex];
    }

    private void evaluatePlay(PlayRps playRps, MatchRps matchRps) {
        if(!isPlayTied(playRps)) {
            setWinner(playRps, matchRps);
        }
    }

    private Boolean isPlayTied(PlayRps playRps ) {
        return playRps.getHandShapePlayer1().equals(playRps.getHandShapePlayer2());
    }

    private void setWinner(PlayRps playRps, MatchRps matchRps) {
        if(playRps.getHandShapePlayer1().equals(HandShape.PAPER)) {
            if(playRps.getHandShapePlayer2().equals(HandShape.ROCK)) {
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        } else if(playRps.getHandShapePlayer1().equals(HandShape.ROCK)) {
            if(playRps.getHandShapePlayer2().equals(HandShape.SCISSORS)) {
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        }else {
            if(playRps.getHandShapePlayer2().equals(HandShape.PAPER)) {
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        }
    }

    private void calculateMatchScore(PlayRps playRps, MatchRps matchRps) {
        if(Objects.nonNull(playRps.getWinnerId())) {
            if(playRps.getWinnerId().equals(matchRps.getPlayer1().getId())) {
                matchRps.setPlayer1Score(matchRps.getPlayer1Score() + 1);
            } else {
                matchRps.setPlayer2Score(matchRps.getPlayer2Score() + 1);
            }
        }
    }

    private void calculateMatchStatus(MatchRps matchRps) {
        matchRps.setRemainingPlays(matchRps.getRemainingPlays() - 1);
        if(matchRps.getRemainingPlays() == 0) {
            matchRps.setStatus(MatchStatus.FINISHED);
            if(!isMatchTied(matchRps)) {
                if(matchRps.getPlayer1Score() > matchRps.getPlayer2Score()) {
                    matchRps.setWinnerId(matchRps.getPlayer1().getId());
                } else {
                    matchRps.setWinnerId(matchRps.getPlayer2().getId());
                }
            }
        }
    }

    private Boolean isMatchTied(MatchRps matchRps) {
        return matchRps.getPlayer1Score().equals(matchRps.getPlayer2Score());
    }
}
