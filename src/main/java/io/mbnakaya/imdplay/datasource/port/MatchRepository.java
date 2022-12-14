package io.mbnakaya.imdplay.datasource.port;

import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.User;

import java.util.List;

public interface MatchRepository {
    Match save(Match match);
    Match getById(Long id);
    List<Match> listByUSer(User user);
}
