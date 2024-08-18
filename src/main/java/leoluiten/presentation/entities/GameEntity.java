package leoluiten.presentation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Lob
    @Column
    private String description;

    @Column
    @Basic(fetch = FetchType.EAGER)
    private String avatar;

    @Lob
    @Column
    private String rules;

    @Column
    private LocalDateTime dateCreated;

    @Column
    private LocalDateTime updatedAt;
}
