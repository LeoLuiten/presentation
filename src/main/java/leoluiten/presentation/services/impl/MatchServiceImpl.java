package leoluiten.presentation.services.impl;

import leoluiten.presentation.entities.MatchEntity;
import leoluiten.presentation.models.Match;
import leoluiten.presentation.repositories.jpa.MatchJpaRepository;
import leoluiten.presentation.services.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Match> getPlayerMatches(Long playerId) {
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
}
