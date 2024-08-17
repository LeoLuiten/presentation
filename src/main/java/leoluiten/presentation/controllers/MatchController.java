package leoluiten.presentation.controllers;

import jakarta.validation.Valid;
import leoluiten.presentation.dtos.match.MatchDTO;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid MatchDTO matchDTO) {
        Match matchSaved = matchService.createMatch(matchDTO);
        if(Objects.isNull(matchSaved)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No match found.");
        } else {
            return ResponseEntity.ok(matchSaved);
        }
    }

}
