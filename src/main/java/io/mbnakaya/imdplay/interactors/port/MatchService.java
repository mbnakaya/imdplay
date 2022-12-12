package io.mbnakaya.imdplay.interactors.port;

import io.mbnakaya.imdplay.domain.Match;

public interface MatchService {
    Match startMatch(String authToken);
}
