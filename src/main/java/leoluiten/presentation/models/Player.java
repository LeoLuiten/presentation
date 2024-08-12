package leoluiten.presentation.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import leoluiten.presentation.utils.validations.password.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Schema(title = "Player'S ID", description = "An unique value for identifying the player", example = "1")
    private Long id;

    @Schema(title = "Player's user name", description = "The player's name that will be displayed to other users", example = "FeelzGood", nullable = false)
    @NotNull(message = "UserName can't be null")
    private String userName;

    @Schema(title = "Player's password", description = "The password for login in", example = "Password#03")
    @NotNull(message = "Password can't be null")
    @ValidPassword
    private String password;

    @Schema(title = "Player's email address", description = "The email account for recovering the password and receiving notifications", example = "email@email.com")
    @NotNull(message = "Email cant be null")
    @Email(message = "The email should be written with a conventional format")
    private String email;

    @Schema(title = "Player's avatar url", description = "The url for the customizable avatar to show in game", example = "https://localhost:8080/avatars/myUsername", nullable = true)
    private String avatar;

    @Schema(title = "Player's last login date", description = "States the last registered player's login date", example = "15-12-2029 13:24:40", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLogin;
}
