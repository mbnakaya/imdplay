package io.mbnakaya.imdplay.datasource.jpa;

import io.mbnakaya.imdplay.datasource.po.MatchPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepositoryJpa extends JpaRepository<MatchPO, Long> { }
