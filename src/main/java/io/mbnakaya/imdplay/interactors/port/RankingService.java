package io.mbnakaya.imdplay.interactors.port;

import io.mbnakaya.imdplay.domain.User;

import java.util.List;

public interface RankingService {
    List<User> getRanking(Integer top);
}
