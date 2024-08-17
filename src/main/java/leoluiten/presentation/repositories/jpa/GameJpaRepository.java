package leoluiten.presentation.repositories.jpa;

import leoluiten.presentation.entities.GameEntity;
import leoluiten.presentation.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {
}
