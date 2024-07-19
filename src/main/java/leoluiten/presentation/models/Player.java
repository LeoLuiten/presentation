package leoluiten.presentation.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Long id;

    @NotNull(message = "UserName can't be null")
    private String userName;
    @NotNull(message = "Password can't be null")
    private String password;

    @NotNull(message = "Email cant be null")
    @Email(message = "The email should be written with a conventional format")
    private String email;
    private String avatar;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLogin;
}
