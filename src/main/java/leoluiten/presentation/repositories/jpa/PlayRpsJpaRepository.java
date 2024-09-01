package leoluiten.presentation.repositories.jpa;

import leoluiten.presentation.entities.PlayRpsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayRpsJpaRepository extends JpaRepository<PlayRpsEntity, Long> {
}
