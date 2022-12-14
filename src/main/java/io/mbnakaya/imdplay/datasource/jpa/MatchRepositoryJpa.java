package io.mbnakaya.imdplay.datasource.jpa;

import io.mbnakaya.imdplay.datasource.po.MatchPO;
import io.mbnakaya.imdplay.datasource.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositoryJpa extends JpaRepository<MatchPO, Long> {
    List<MatchPO> findAllByUser(UserPO user);
}
