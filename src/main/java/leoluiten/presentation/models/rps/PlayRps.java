package leoluiten.presentation.models.rps;

import leoluiten.presentation.models.Play;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayRps implements Play {

    private Long id;
    private Long matchRpsId;
    private HandShape handShapePlayer1;
    private HandShape handShapePlayer2;
    private Long winnerId;
}
