package leoluiten.presentation.entities;

import jakarta.persistence.*;
import leoluiten.presentation.models.rps.HandShape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plays_rps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayRpsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "match_rps_id")
    private MatchRpsEntity matchRps;

    @Enumerated(EnumType.STRING)
    private HandShape handShapePlayer1;

    @Enumerated(EnumType.STRING)
    private HandShape handShapePlayer2;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private PlayerEntity winner;
}
