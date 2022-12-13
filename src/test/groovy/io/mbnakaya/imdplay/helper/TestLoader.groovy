package io.mbnakaya.imdplay.helper

import io.mbnakaya.imdplay.domain.Login
import io.mbnakaya.imdplay.domain.Match
import io.mbnakaya.imdplay.domain.MatchStatus
import io.mbnakaya.imdplay.domain.Movie
import io.mbnakaya.imdplay.domain.User

class TestLoader {

    static User generateEmptyUser() {
        return User.builder()
            .id(0)
            .fullName("Full Name")
            .userName("UserName")
            .email("test@mail.com")
            .password("1234")
            .score(0L)
            .build()
    }

    static User generatePersistedUser() {
        return User.builder()
            .id(1)
            .fullName("Full Name")
            .userName("UserName")
            .email("test@mail.com")
            .password("1234")
            .score(0L)
            .build()
    }

    static Login generateBasicLogin() {
        return Login.builder()
            .user(generateEmptyUser())
            .authToken("dGVzdDp0ZXN0")
            .build()
    }

    static Movie generateMovieA() {
        return Movie.builder()
                .id(7L)
                .tittle("Jurassic Park")
                .genre("Action, Adventure, Sci-Fi")
                .year(1993)
                .director("Steven Spielberg")
                .rated("PG-13")
                .imdbRating(8.2)
                .votes(985724)
                .build()
    }

    static Movie generateMovieB() {
        return Movie.builder()
                .id(6L)
                .tittle("Star Wars: Episode V - The Empire Strikes Back")
                .genre("Action, Adventure, Fantasy")
                .year(1980)
                .director("Irvin Kershner")
                .rated("PG")
                .imdbRating(8.7)
                .votes(1287175)
                .build()
    }

    static Match generateMatch() {
        Match match = Match.generateNewOne(generatePersistedUser())
        match.setId(1L)
        match.setMovieA(generateMovieA())
        match.setMovieB(generateMovieB())

        return match;
    }

    static List<Match> generateMatches() {
        Match match = Match.generateNewOne(generatePersistedUser())
        match.setId(1L)
        match.setMovieA(generateMovieA())
        match.setMovieB(generateMovieB())

        return Arrays.asList(match)
    }

    static Match generateScoredMatch() {
        Match match = Match.generateNewOne(generatePersistedUser())
        match.setId(1L)
        match.setMovieA(generateMovieA())
        match.setMovieB(generateMovieB())
        match.setPoints(10)

        return match;
    }

    static Match generateFinishedMatch() {
        Match match = Match.generateNewOne(generatePersistedUser())
        match.setId(1L)
        match.setStatus(MatchStatus.FINISHED)

        return match;
    }

    static Match generateMatchWrightAnswer() {
        Match match = Match.generateNewOne(generatePersistedUser())
        match.setId(1L)
        match.setMovieA(generateMovieA())
        match.setMovieB(generateMovieB())
        match.positiveResult()

        return match;
    }

    static Match generateMatchWrongAnswer() {
        Match match = Match.generateNewOne(generatePersistedUser())
        match.setId(1L)
        match.setMovieA(generateMovieA())
        match.setMovieB(generateMovieB())
        match.negativeResult()
        match.reduceChance()

        return match;
    }
}
