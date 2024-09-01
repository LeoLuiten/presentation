package leoluiten.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import leoluiten.presentation.dtos.common.ErrorApi;
import leoluiten.presentation.dtos.MatchDTO;
import leoluiten.presentation.dtos.play.PlayRequest;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.Play;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;


/**
 * REST controller for managing matches. Provides endpoints to retrieve
 * match data.
 */
@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;


    @Operation(
            summary = "Create a new match",
            description = "Return the match created with information on the game and player involved in it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Match registered successfully", content =
            @Content(schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "400", description = "Invalid player and/or match", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid MatchDTO matchDTO) {
        Match matchSaved = matchService.createMatch(matchDTO);
        if (Objects.isNull(matchSaved)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No match found.");
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(matchSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(matchSaved);
        }
    }

    @Operation(
            summary = "Gets a match by its id.",
            description = "Returns either a matching Match or a 404 status code error."
    )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse( responseCode = "404", description = "Not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse( responseCode = "500", description = "Internal server error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Match> getById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PostMapping("{id}/plays/")
    public ResponseEntity<Play> saveMatch(@PathVariable Long id, @RequestBody @Valid PlayRequest playRequest) {
        Play playResult = matchService.play(id, playRequest);
        if(Objects.isNull(playResult)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error resolving the request");
        } else {
            return ResponseEntity.ok(playResult);
        }
    }
}
