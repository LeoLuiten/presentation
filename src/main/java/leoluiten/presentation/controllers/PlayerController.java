package leoluiten.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import leoluiten.presentation.dtos.common.ErrorApi;
import leoluiten.presentation.dtos.PlayerDTO;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.MatchService;
import leoluiten.presentation.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing players. Provides endpoints to retrieve
 * and save player data.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @Operation(
            summary = "Gets a player by its id.",
            description = "Returns either a matching Player or a 404 status code error."
    )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Successful operation", content =
                @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse( responseCode = "404", description = "Not found", content =
                @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse( responseCode = "500", description = "Internal server error", content =
                @Content(schema = @Schema(implementation = ErrorApi.class)))
    })

    /**
     * Retrieves a player by their unique ID.
     *
     * @param id the ID of the player to retrieve.
     * @return a {@link ResponseEntity} containing the {@link Player} object corresponding to the provided ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @Operation(
            summary = "Create a new player",
            description = "Return the player created with your id. If a player matching the username or email exists, then it will return 400. " +
                    "Additionally, the email must be valid and the password must have at least 8 characters while containing at least one number, " +
                    "one lowercase letter, one uppercase letter, and one special character.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player created successfully", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Username or email already exists", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })

    @PostMapping("")
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid PlayerDTO player) {
        Player playerSaved = playerService.savePlayer(player);
        if (Objects.isNull(playerSaved)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email already exists");
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(playerSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(playerSaved);
        }
    }

    @Operation(
            summary = "Get matches of a player",
            description = "Returns a list of matches associated with a specific player based on their ID. " +
                    "If the player does not exist, it will return a 404 error."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))),
            @ApiResponse(responseCode = "404", description = "Player not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("{id}/matches")
    public ResponseEntity<List<Match>> getMatchesOfPlayer(@PathVariable Long id) {
        List<Match> matches = matchService.getMatchesByPlayer(id);
        return ResponseEntity.ok(matches);
    }
}
