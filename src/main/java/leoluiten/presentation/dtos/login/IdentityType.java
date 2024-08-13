package leoluiten.presentation.dtos.login;
/**
 * The {@code IdentityType} enum defines the various types of user identifiers
 * that can be used along with a password to verify a user's login credentials.
 *
 * <p>The {@code IdentityType} enum currently supports the following values:</p>
 * <ul>
 *     <li>{@link #USERNAME} - The user's username, typically a unique identifier chosen by the user.</li>
 *     <li>{@link #EMAIL} - The user's email address, which is often unique and used aas a login credential.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 *     IdentityType identityType = IdentityType.USERNAME;
 * </pre>
 *
 * @see Identity
 * @see Credential
 */
public enum IdentityType {

    /**
     * Represents the use's username, a unique identifier chosen by the user.
     */
    USERNAME,

    /**
     * Represents the user's email address, often used as a unique login credential.
     */
    EMAIL;
}
