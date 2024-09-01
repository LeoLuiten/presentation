package leoluiten.presentation.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import leoluiten.presentation.models.rps.MatchRps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents an abstract game match.
 * <p>
 * This class serves as a base for different types of game matches. It is abstract and cannot be
 * instantiated directly. The specific subclass of {@code Match} is determined by the type of game
 * associated with the match, using Jackson annotations to handle polymorphic JSON serialization
 * and deserialization.
 * </p>
 * <p>
 * The {@link JsonTypeInfo} and {@link JsonSubTypes} annotations are used to define how Jackson
 * should handle the deserialization of subclasses. Currently, {@link MatchRps} is a known subclass
 * of {@code Match}.
 * </p>
 *
 * <p>Key Fields:</p>
 * <ul>
 *   <li>{@code id}: Unique identifier for the match.</li>
 *   <li>{@code game}: The game associated with this match.</li>
 *   <li>{@code player}: The player participating in this match.</li>
 *   <li>{@code createdAt}: The date and time when the match was created.</li>
 *   <li>{@code status}: The current status of the match.</li>
 * </ul>
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@link JsonTypeInfo}: Specifies how Jackson should include type information during serialization.</li>
 *   <li>{@link JsonSubTypes}: Defines the subtypes of {@code Match} for polymorphic deserialization.</li>
 * </ul>
 *
 * @see leoluiten.presentation.models.rps.MatchRps
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MatchRps.class),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Match {

    private Long id;
    private Game game;
    private Player player1;
    private Player player2;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;

    private MatchStatus status;
}
