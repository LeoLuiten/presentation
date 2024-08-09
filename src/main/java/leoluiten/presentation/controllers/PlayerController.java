package leoluiten.presentation.controllers;

import jakarta.validation.Valid;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing players. Provides endpoints to retrieve
 * and save player data.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

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

    /**
     * Saves a player's data.
     *
     * @param player the {@link Player} object containing the player's data to be saved.
     * @return a {@link ResponseEntity} containing the saved {@link Player} object.
     */
    @PostMapping("")
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player) {
        return ResponseEntity.ok(playerService.savePlayer(player));
    }
}
