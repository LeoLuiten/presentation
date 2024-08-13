package leoluiten.presentation.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a username-based identity for user authentication.
 * <p>
 * The {@code UserNameIdentity} class extends the {@link Identity} class and is used when the user's identity
 * is specified by a username. This class is part of the polymorphic structure that allows the handling of
 * different types of user identities during the login process.
 * </p>
 *
 * <p>
 * It includes:
 * <ul>
 *   <li>A field for the username used for login. This field is validated to ensure it is not null.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class works together with the {@link Credential} class to represent and manage user credentials
 * where the identity is a username.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>
 *     // Create a UserNameIdentity instance
 *     UserNameIdentity userNameIdentity = new UserNameIdentity("john_doe");
 *
 *     // Create a Credential using UserNameIdentity
 *     Credential credential = new Credential(userNameIdentity, "password123");
 * </pre>
 *
 * @see Identity
 * @see Credential
 * @see EmailIdentity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameIdentity extends Identity {

    /**
     * The username used for login.
     * This field is required and must not be null.
     */
    @Schema(title = "Username to log in",
            description = "The username used for authentication. This must be a unique identifier chosen by the user.",
            example = "john_doe",
            nullable = false)
    @NotNull(message = "Username cannot be null")
    @JsonProperty("user_name")
    private String username;
}
