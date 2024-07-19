package leoluiten.presentation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    @Basic(fetch = FetchType.EAGER)
    private String avatar;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private LocalDateTime dateCreated;

    @Column
    private LocalDateTime updatedAt;

}
