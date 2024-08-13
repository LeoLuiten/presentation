package leoluiten.presentation.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an email-based identity for user authentication.
 * <p>
 * The {@code EmailIdentity} class extends the {@link Identity} class and is used when the user's identity
 * is specified by an email address. This class is part of the polymorphic structure for handling different
 * types of user identities during authentication.
 * </p>
 *
 * <p>
 * It includes:
 * <ul>
 *   <li>A field for the email address used for login. This field is validated to ensure it is not null.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class is used in conjunction with the {@link Credential} class to represent and handle user credentials
 * where the identity is an email address.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>
 *     // Create an EmailIdentity instance
 *     EmailIdentity emailIdentity = new EmailIdentity("email@example.com");
 *
 *     // Create a Credential using EmailIdentity
 *     Credential credential = new Credential(emailIdentity, "password123");
 * </pre>
 *
 * @see Identity
 * @see Credential
 * @see UserNameIdentity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailIdentity extends Identity {

    /**
     * The email address used for login.
     * This field is required and should not be null.
     */
    @Schema(title = "Email to log in",
            description = "The email address used to log in. This must be a valid email address.",
            example = "email@example.com",
            nullable = false)
    @NotNull(message = "Email address cannot be null")
    @JsonProperty("email")
    private String email;
}
