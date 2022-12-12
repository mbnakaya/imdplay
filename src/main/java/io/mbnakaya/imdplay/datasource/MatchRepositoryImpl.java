package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.jpa.MatchRepositoryJpa;
import io.mbnakaya.imdplay.datasource.po.MatchPO;
import io.mbnakaya.imdplay.datasource.po.UserPO;
import io.mbnakaya.imdplay.datasource.port.MatchRepository;
import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.MatchStatus;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.domain.exceptions.MatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MatchRepositoryImpl implements MatchRepository {

    @Autowired
    private MatchRepositoryJpa repositoryJpa;

    @Override
    public Match save(Match match) {
        MatchPO matchPO = MatchPO.toPO(match);

        if (matchPO.getStatus().equals(MatchStatus.FINISHED)) {
            matchPO.setMovieA(null);
            matchPO.setMovieB(null);
        }
        return repositoryJpa.save(matchPO).toDomain();
    }

    @Override
    public Match getById(Long id) {
        Optional<MatchPO> result = repositoryJpa.findById(id);

        if (result.isPresent()) return result.get().toDomain();
        throw new MatchNotFoundException();
    }

    @Override
    public List<Match> listByUSer(User user) {
        List<MatchPO> matches = repositoryJpa.findAllByUser(UserPO.toPO(user));
        return MatchPO.toDomainList(matches);
    }
}
