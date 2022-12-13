package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {

    private final UserRepository userRepository;

    public RankingServiceImpl(UserRepository bean) {
        this.userRepository = bean;
    }
    @Override
    public List<User> getRanking(Integer top) {
        return userRepository.getRanking(top);
    }
}
