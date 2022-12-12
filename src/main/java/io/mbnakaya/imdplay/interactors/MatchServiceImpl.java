package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.MatchRepository;
import io.mbnakaya.imdplay.datasource.port.MovieRepository;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.Movie;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import io.mbnakaya.imdplay.interactors.port.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Match startMatch(String authToken) {
        String userName = authenticationService.getUserName(authToken);
        Match newMatch = Match.generateNewOne(getUserByUserName(userName));
        newMatch.setMovieA(getMovieRandomly());
        newMatch.setMovieB(getMovieRandomlyExcept(newMatch.getMovieA().getId()));

        Long matchId = matchRepository.save(newMatch);

        newMatch.setId(matchId);
        return newMatch;
    }

    private Movie getMovieRandomly() {
        Long movieId = new Random().nextLong(0, movieRepository.getMovieListSize());
        return movieRepository.getById(movieId);
    }

    private Movie getMovieRandomlyExcept(Long excluded) {
        Long movieId = new Random().nextLong(0, movieRepository.getMovieListSize());

        while (excluded.equals(movieId)) {
            movieId = new Random().nextLong(0, movieRepository.getMovieListSize());
        }
        return movieRepository.getById(movieId);
    }

    private User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
