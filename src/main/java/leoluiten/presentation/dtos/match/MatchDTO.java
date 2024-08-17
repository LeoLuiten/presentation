package leoluiten.presentation.dtos.match;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    @NotNull
    private Long gameId;

    @NotNull
    private Long playerId;
}
