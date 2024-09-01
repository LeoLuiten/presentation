package leoluiten.presentation.dtos.play;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import leoluiten.presentation.models.rps.HandShape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayRpsDto implements PlayRequest{

    @NotNull
    @JsonProperty("hand_shape_player_1")
    private HandShape handShapePlayer1;

    @JsonProperty("hand_shape_player_2")
    private HandShape handShapePlayer2;
}
