package leoluiten.presentation.dtos.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the credentials required for user login.
 * <p>
 * The {@code Credential} class encapsulates the information needed for a user to authenticate. It includes:
 * <ul>
 *   <li>An {@link Identity} object, which represents the user's identity and can be either a username or an email address.</li>
 *   <li>A {@code password} string that the user provides along with their identity to verify their credentials.</li>
 * </ul>
 * </p>
 *
 * <p>
 * When performing a login operation, an instance of this class is used to hold and process the user's credentials.
 * This allows the system to validate the identity and password provided by the user against stored values.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>
 *     // Create a new credential with a username and password
 *     Credential credential = new Credential(new UserNameIdentity("john_doe"), "password123");
 *
 *     // Create a new credential with an email and password
 *     Credential credential = new Credential(new EmailIdentity("john.doe@example.com"), "password123");
 * </pre>
 *
 * @see Identity
 * @see UserNameIdentity
 * @see EmailIdentity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    /**
     * The user's identity, which can be either a username or an email address.
     * This is used in conjunction with the password to authenticate the user.
     */
    private Identity identity;

    /**
     * The password associated with the user's identity.
     * This is used to verify the authenticity of the user's login attempt.
     */
    private String password;
}
