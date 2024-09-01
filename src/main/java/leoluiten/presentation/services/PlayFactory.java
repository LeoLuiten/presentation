package leoluiten.presentation.services;

import leoluiten.presentation.dtos.play.PlayRequest;
import leoluiten.presentation.dtos.play.PlayRpsDto;
import leoluiten.presentation.models.Play;
import leoluiten.presentation.models.rps.PlayRps;

public class PlayFactory {

    public static Play getPlayInstance(PlayRequest playRequest, String gameCode) {
        switch (gameCode) {
            case "RPS":
                return getPlayRpsInstance(playRequest);
            default:
                return getPlayRpsInstance(playRequest);
        }
    }

    private static Play getPlayRpsInstance(PlayRequest playRequest) {
        PlayRpsDto playRpsDto = (PlayRpsDto) playRequest;
        PlayRps playRps = new PlayRps();
        playRps.setHandShapePlayer1(playRpsDto.getHandShapePlayer1());
        playRps.setHandShapePlayer2(playRpsDto.getHandShapePlayer2());
        return playRps;
    }
}
