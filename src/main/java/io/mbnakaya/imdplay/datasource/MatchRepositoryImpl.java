package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.jpa.MatchRepositoryJpa;
import io.mbnakaya.imdplay.datasource.po.MatchPO;
import io.mbnakaya.imdplay.datasource.port.MatchRepository;
import io.mbnakaya.imdplay.domain.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchRepositoryImpl implements MatchRepository {

    @Autowired
    private MatchRepositoryJpa repositoryJpa;

    @Override
    public Long save(Match match) {
        return repositoryJpa.save(MatchPO.toPO(match)).getId();
    }
}
