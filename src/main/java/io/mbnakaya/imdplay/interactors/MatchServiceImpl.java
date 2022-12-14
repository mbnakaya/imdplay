package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.MatchRepository;
import io.mbnakaya.imdplay.datasource.port.MovieRepository;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.*;
import io.mbnakaya.imdplay.domain.exceptions.InvalidResultException;
import io.mbnakaya.imdplay.domain.exceptions.MatchAlreadyFinishedException;
import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import io.mbnakaya.imdplay.interactors.port.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {

    private final UserRepository userRepository;

    private final MatchRepository matchRepository;

    private final MovieRepository movieRepository;

    private final AuthenticationService authenticationService;

    public MatchServiceImpl(UserRepository userRepositoryBean, MatchRepository matchRepositoryBean, MovieRepository movieRepositoryBean, AuthenticationService authenticationServiceBean) {
        this.userRepository = userRepositoryBean;
        this.matchRepository = matchRepositoryBean;
        this.movieRepository = movieRepositoryBean;
        this.authenticationService = authenticationServiceBean;
    }

    @Override
    public Match startMatch(String authToken) {
        String userName = authenticationService.getUserName(authToken);
        Match newMatch = Match.generateNewOne(getUserByUserName(userName));
        newMatch.setMovieA(getMovieRandomly());
        newMatch.setMovieB(getMovieRandomlyExcept(newMatch.getMovieA().getId()));

        List<String> answered = new ArrayList<>();
        answered.add(newMatch.getMovieA().getId().toString());
        answered.add(newMatch.getMovieB().getId().toString());

        newMatch.setAnswered(answered);

        Match persistedMatch = matchRepository.save(newMatch);
        return loadMovieData(persistedMatch);
    }

    @Override
    public Match processResponse(Long matchId, Response response) {
        Match match = matchRepository.getById(matchId);
        if (match.getStatus().equals(MatchStatus.FINISHED)) throw new MatchAlreadyFinishedException();

        List<String> answered = new ArrayList<>(match.getAnswered());
        match = loadMovieData(match);

        if (checkResponse(match, response)) {
            match = nextRound(match, response);
            match.positiveResult();
        }
        else {
            match = nextRound(match, response);
            match.reduceChance();
            match.negativeResult();
        }

        if (match.getChances().equals(0)) return finishMatch(matchId);
        match.setStatus(MatchStatus.IN_PROGRESS);

        Match persistedMatch = matchRepository.save(match);
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
        List<Match> matches = matchRepository.listByUSer(user);
        return loadMovieData(matches);
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

    private Movie getMovieRandomlyExcept(List<String> excluded) {
        Long movieId = new Random().nextLong(0, movieRepository.getMovieListSize());

        while (excluded.contains(movieId.toString())) {
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

    private Boolean checkResponse(Match match, Response response) {
        Double movieA = (match.getMovieA().getImdbRating()) * (match.getMovieA().getVotes());
        Double movieB = (match.getMovieB().getImdbRating()) * (match.getMovieB().getVotes());
        Boolean result;

        switch (response) {
            case A -> result = movieA > movieB;
            case B -> result = movieB > movieA;
            default -> result = false;
        }
        return result;
    }

    private Match nextRound(Match match, Response response) {
        switch (response) {
            case A -> {
                Movie newMovie = getMovieRandomlyExcept(match.getAnswered());
                match.setMovieB(newMovie);
                match.addAnswered(newMovie.getId());
            }
            case B -> {
                Movie newMovie = getMovieRandomlyExcept(match.getAnswered());
                match.setMovieA(newMovie);
                match.addAnswered(newMovie.getId());
            }
            default -> throw new InvalidResultException();
        }
        return match;
    }
}
