package leoluiten.presentation.entities;

import jakarta.persistence.*;
import leoluiten.presentation.models.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="game_id")
    @ManyToOne
    private GameEntity game;

    @JoinColumn(name="player1_id")
    @ManyToOne
    private PlayerEntity player1;

    @JoinColumn(name="player2_id")
    @ManyToOne
    private PlayerEntity player2;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
}
