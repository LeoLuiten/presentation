package leoluiten.presentation.repositories.jpa;

import leoluiten.presentation.entities.PlayerEntity;
import leoluiten.presentation.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link PlayerEntity} entities.
 * Provides methods to perform CRUD operations and custom queries
 * using Spring Data JPA.
 */
@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Long> {

    /**
     * Retrieves a {@link PlayerEntity} based on the provided username or email.
     * This method returns an {@link Optional} that contains the found entity,
     * or an empty {@link Optional} if no entity matches either the username or email.
     *
     * @param username the username to check for existence. Can be null or empty if searching by email only.
     * @param email the email to check for existence. Can be null or empty if searching by username only.
     * @return an {@link Optional<PlayerEntity>} containing the matching entity, or empty if no match is found.
     */
    Optional<PlayerEntity> findByUserNameOrEmail(String username, String email);

    Optional<PlayerEntity> findByUserNameAndPassword(String userName, String password);

    Optional<PlayerEntity> findByEmailAndPassword(String email, String password);

    @Query("SELECT p FROM PlayerEntity p " +
            "WHERE (p.userName LIKE :identity OR p.email LIKE :identity) AND p.password LIKE :password")
    Optional<PlayerEntity> findByUserNameOrEmailAndPassword(String identity, String password);
}
