package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Match startMatch(String authToken) {
        String userName = authenticationService.getUserName(authToken);
        Match match = Match.generateNewOne(getUserByUserName(userName));
        return null;
    }

    private User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
