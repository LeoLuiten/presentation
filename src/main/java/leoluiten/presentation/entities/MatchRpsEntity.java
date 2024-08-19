package leoluiten.presentation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "matches_rps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRpsEntity extends MatchEntity {

    @Column
    private Long id;

    @Column
    private Integer numberOfPlays;

    @Column
    private Integer remainingPlays;

    @Column
    private Integer player1Score;

    @Column
    private Integer player2Score;

    @OneToMany(mappedBy = "matchRps")
    private List<PlayRpsEntity> plays;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private PlayerEntity winner;
}
