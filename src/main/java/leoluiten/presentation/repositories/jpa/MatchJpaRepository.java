package leoluiten.presentation.repositories.jpa;

import leoluiten.presentation.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {

    @Query("SELECT m FROM MatchEntity m " +
            "WHERE m.player1.id = :playerId OR m.player2.id = :playerId")
    List<MatchEntity> getAllByPlayerId(Long playerId);
}

