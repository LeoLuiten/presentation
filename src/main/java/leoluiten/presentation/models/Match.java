package leoluiten.presentation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;
    private Game game;
    private Player player;
    private LocalDateTime createdDate;
    private MatchStatus status;
}
