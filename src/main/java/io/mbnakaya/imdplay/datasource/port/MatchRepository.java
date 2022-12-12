package io.mbnakaya.imdplay.datasource.port;

import io.mbnakaya.imdplay.domain.Match;

public interface MatchRepository {
    Long save(Match match);
}
