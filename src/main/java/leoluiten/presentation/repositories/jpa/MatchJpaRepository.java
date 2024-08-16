package leoluiten.presentation.repositories.jpa;

import leoluiten.presentation.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {

    @Query("SELECT m FROM MatchEntity m WHERE m.player.id = :playerId")
    Optional<List<MatchEntity>> getAllByPlayerId(Long playerId);
}
