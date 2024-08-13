package leoluiten.presentation.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code Identity} class is an abstract base class that represents different types of user identities
 * used for login, specifically a username or an email address. This class is part of a polymorphic
 * structure that allows the system to accept different identity types while maintaining a consistent interface.
 *
 * <p>This class is extended by {@link UserNameIdentity} and {@link EmailIdentity} which provide concrete
 * implementations for username and email-based identities, respectively. The {@code identityType} field
 * is used to distinguish between these two types during JSON serialization and deserialization.</p>
 *
 * <p>The Jackson annotations {@link JsonTypeInfo} and {@link JsonSubTypes} enable polymorphic deserialization.
 * The {@code identity_type} property in the JSON data determines whether the JSON should be deserialized into
 * a {@code UserNameIdentity} or an {@code EmailIdentity} instance.</p>
 *
 * <p>For example, when {@code identity_type} is "USERNAME", the JSON will be deserialized into a {@link UserNameIdentity}.
 * Conversely, when {@code identity_type} is "EMAIL", it will be deserialized into an {@link EmailIdentity}.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @JsonTypeInfo} - Specifies the mechanism for identifying the concrete subclass during deserialization.</li>
 *   <li>{@code @JsonSubTypes} - Defines the mapping of possible subclass types based on the value of {@code identity_type}.</li>
 *   <li>{@code @Schema} - Provides metadata for API documentation, particularly useful for tools like Swagger/OpenAPI.</li>
 *   <li>{@code @NotNull} - Ensures that the {@code identityType} field is never null, which is important for correct processing.</li>
 *   <li>{@code @JsonProperty} - Specifies the name of the JSON property that corresponds to the {@code identityType} field.</li>
 *   <li>{@code @Data}, {@code @AllArgsConstructor}, {@code @NoArgsConstructor} - Lombok annotations to automatically generate boilerplate code.</li>
 * </ul>
 *
 * <p>Example JSON structure:</p>
 * <pre>
 * {
 *   "identity_type": "USERNAME",
 *   "user_name": "john_doe"
 * }
 * </pre>
 *
 * <p>In this example, the JSON would be deserialized into a {@code UserNameIdentity} object.</p>
 *
 * @see UserNameIdentity
 * @see EmailIdentity
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "identity_type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserNameIdentity.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = EmailIdentity.class, name = "EMAIL")}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Identity {

    /**
     * The type of identity used for login, such as a username or email.
     * This field determines the specific implementation of {@code Identity}
     * to be used during deserialization.
     */
    @Schema(title = "Type of identity used to log in",
            description = "The type of credential provided to log in, such as USERNAME or EMAIL",
            example = "USERNAME or EMAIL")
    @NotNull(message = "identity_type can't be null")
    @JsonProperty("identity_type")
    private IdentityType identityType;
}
