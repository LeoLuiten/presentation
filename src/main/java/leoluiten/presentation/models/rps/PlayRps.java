package leoluiten.presentation.models.rps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayRps {

    private Long id;
    private Long matchRpsId;
    private HandShape handShapePlayer1;
    private HandShape handShapePlayer2;
    private Long winnerId;
}
