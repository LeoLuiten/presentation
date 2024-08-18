package leoluiten.presentation.services.impl;

import jakarta.persistence.EntityNotFoundException;
import leoluiten.presentation.dtos.MatchDTO;
import leoluiten.presentation.entities.MatchEntity;
import leoluiten.presentation.entities.PlayerEntity;
import leoluiten.presentation.models.Game;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.models.MatchStatus;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.repositories.jpa.MatchJpaRepository;
import leoluiten.presentation.services.GameService;
import leoluiten.presentation.services.MatchService;
import leoluiten.presentation.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GameService gameService;

    @Override
    public List<Match> getMatchesByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();
        Optional<List<MatchEntity>> optionalMatchEntities = matchJpaRepository.getAllByPlayerId(playerId);
//        if (optionalMatchEntities.isPresent()) {
//            optionalMatchEntities.get().forEach(
//                    me -> {matches.add(modelMapper.map(me, Match.class));}
//            );
//        }
        optionalMatchEntities.ifPresent(matchEntities -> matchEntities.forEach(
                me -> {
                    matches.add(modelMapper.map(me, Match.class));
                }
        ));
        return matches;
    }

    @Override
    public Match createMatch(MatchDTO matchDTO) {
        Match match = new Match();
        Player player = playerService.getPlayerById(matchDTO.getPlayerId());
        Game game = gameService.getGame(matchDTO.getGameId());
        match.setPlayer(player);
        match.setGame(game);
        match.setCreatedAt(LocalDateTime.now());
        match.setStatus(MatchStatus.STARTED);
        MatchEntity matchEntitySaved = matchJpaRepository.save(
                modelMapper.map(match, MatchEntity.class));
        return modelMapper.map(matchEntitySaved, Match.class);

    }

    /**
     * Retrieves a match by its ID, converting the entity to a model object.
     *
     * @param id the ID of the match to retrieve.
     * @return the {@link Match} model object corresponding to the retrieved match entity.
     */
    @Override
    public Match getMatchById(Long id) {
        MatchEntity matchEntity = matchJpaRepository.getReferenceById(id);
        if(Objects.isNull(matchEntity.getGame())) {
            throw new EntityNotFoundException();
        } else {
            return modelMapper.map(matchEntity, Match.class);
        }
    }
}
