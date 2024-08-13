package leoluiten.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import leoluiten.presentation.dtos.common.ErrorApi;
import leoluiten.presentation.dtos.login.Credential;
import leoluiten.presentation.dtos.login.CredentialV2;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(
            summary = "Login a player on the platform",
            description = "Returns the player logged in if the credentials are valid"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "The credentials are invalid", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid Credential credential) {
        return ResponseEntity.ok(loginService.login(credential));
    }

    @Operation(
            summary = "Login a player on the platform",
            description = "Returns the player logged in if the credentials are valid"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "The credentials are invalid", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("/v2")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid CredentialV2 credentialV2){
        return ResponseEntity.ok(loginService.login(credentialV2));
    }
}
