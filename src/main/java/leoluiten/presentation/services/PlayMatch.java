package leoluiten.presentation.services;

import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.Play;
import org.springframework.stereotype.Service;

/**
 *
 * @param <P> The Play response
 * @param <M> The Match to play
 */
@Service
public interface PlayMatch <P extends Play, M extends Match> {

    P play(P play, M match);
}
