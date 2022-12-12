package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.MatchRepository;
import io.mbnakaya.imdplay.datasource.port.MovieRepository;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.MatchStatus;
import io.mbnakaya.imdplay.domain.Movie;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.domain.exceptions.MatchAlreadyFinishedException;
import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import io.mbnakaya.imdplay.interactors.port.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        Match persistedMatch = matchRepository.save(newMatch);
        return loadMovieData(persistedMatch);
    }

    @Override
    public Match finishMatch(Long matchId) {
        Match match = matchRepository.getById(matchId);

        if (match.getStatus().equals(MatchStatus.FINISHED)) throw new MatchAlreadyFinishedException();
        match.setStatus(MatchStatus.FINISHED);
        match.getUser().addScore(match.getPoints().longValue());

        return matchRepository.save(match);
    }

    @Override
    public Match getMatch(Long matchId) {
        Match match = matchRepository.getById(matchId);
        return loadMovieData(match);
    }

    @Override
    public List<Match> listMatches(String authToken) {
        String userName = authenticationService.getUserName(authToken);
        User user = userRepository.findByUserName(userName);
        return matchRepository.listByUSer(user);
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

    private Match loadMovieData(Match match) {
        if (match.getStatus().equals(MatchStatus.FINISHED)) return match;
        match.setMovieA(movieRepository.getById(match.getMovieA().getId()));
        match.setMovieB(movieRepository.getById(match.getMovieB().getId()));

        return match;
    }

    private List<Match> loadMovieData(List<Match> matches) {
        matches.forEach(match -> {
            match.setMovieA(movieRepository.getById(match.getMovieA().getId()));
            match.setMovieB(movieRepository.getById(match.getMovieB().getId()));
        });

        return matches;
    }
}
