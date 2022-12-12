package io.mbnakaya.imdplay.interactors.port;

import io.mbnakaya.imdplay.domain.Match;

import java.util.List;

public interface MatchService {
    Match startMatch(String authToken);
    Match finishMatch(Long matchId);
    Match getMatch(Long matchId);
    List<Match> listMatches(String authToken);
}
