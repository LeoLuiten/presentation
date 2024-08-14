package leoluiten.presentation.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialV2 {

    @Schema(title = "Email or UserName to log in",
            description = "The player email or username",
            example = "email@email.com or username"
    )
    @NotNull(message = "Identity can't be null")
    @JsonProperty("identity")
    private String identity;

    @NotNull(message = "Password can't be null")
    private String password;
}
