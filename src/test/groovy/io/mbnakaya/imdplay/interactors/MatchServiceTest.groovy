package io.mbnakaya.imdplay.interactors

import io.mbnakaya.imdplay.datasource.MatchRepositoryImpl
import io.mbnakaya.imdplay.datasource.MovieRepositoryImpl
import io.mbnakaya.imdplay.datasource.UserRepositoryImpl
import io.mbnakaya.imdplay.domain.Match
import io.mbnakaya.imdplay.domain.MatchStatus
import io.mbnakaya.imdplay.domain.Response
import io.mbnakaya.imdplay.domain.User
import io.mbnakaya.imdplay.domain.exceptions.MatchAlreadyFinishedException
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class MatchServiceTest extends Specification {

    MatchServiceImpl service
    UserRepositoryImpl userRepository = Mock()
    MatchRepositoryImpl matchRepository = Mock()
    MovieRepositoryImpl movieRepository = Mock()
    AuthenticationServiceImpl authenticationService = Mock()

    def setup() {
        service = new MatchServiceImpl(userRepository, matchRepository, movieRepository, authenticationService)
    }

    def "Should start a new match"() {
        given:
        String authToken = "dGVzdDp0ZXN0"

        when:
        Match result = service.startMatch(authToken)

        then:
        1 * authenticationService.getUserName(authToken) >> "test"
        1 * userRepository.findByUserName(_ as String) >> TestLoader.generatePersistedUser()
        2 * movieRepository.getMovieListSize() >> 10
        4 * movieRepository.getById(_ as Long) >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()] >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()]
        1 * matchRepository.save(_ as Match) >> TestLoader.generateMatch()

        result.getMovieA() != result.getMovieB()
        result.getStatus() == MatchStatus.CREATED
    }

    def "Should process response with wright answer"() {
        given:
        Long matchId = 1L
        Response response = Response.B

        when:
        Match match = service.processResponse(matchId, response)

        then:
        1 * matchRepository.getById(matchId) >> TestLoader.generateMatch()
        5 * movieRepository.getById(_ as Long) >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()] >> TestLoader.generateMovieA() >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()]
        1 * movieRepository.getMovieListSize() >> 10
        1 * matchRepository.save(_ as Match) >> TestLoader.generateMatchWrightAnswer()

        match.getPoints() > 0
        match.getResult() == "O"
        match.getChances() == 3
    }

    def "Should process response with wrong answer"() {
        given:
        Long matchId = 1L
        Response response = Response.A

        when:
        Match match = service.processResponse(matchId, response)

        then:
        1 * matchRepository.getById(matchId) >> TestLoader.generateMatch()
        5 * movieRepository.getById(_ as Long) >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()] >> TestLoader.generateMovieA() >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()]
        1 * movieRepository.getMovieListSize() >> 10
        1 * matchRepository.save(_ as Match) >> TestLoader.generateMatchWrongAnswer()

        match.getPoints() == 0
        match.getResult() == "X"
        match.getChances() < 3
    }

    def "Should fail to process response because match is already finished"() {
        given:
        Long matchId = 1L
        Response response = Response.B

        when:
        service.processResponse(matchId, response)

        then:
        1 * matchRepository.getById(matchId) >> TestLoader.generateFinishedMatch()

        def e = thrown(MatchAlreadyFinishedException)
        e.getMessage() == "This match is already finished."
    }

    def "Should finish match"() {
        given:
        Long matchId = 1L

        when:
        service.finishMatch(matchId)

        then:
        1 * matchRepository.getById(matchId) >> TestLoader.generateScoredMatch()
        1 * matchRepository.save(_ as Match) >> TestLoader.generateFinishedMatch()
    }

    def "Should fail to finish match"() {
        given:
        Long matchId = 1L

        when:
        service.finishMatch(matchId)

        then:
        1 * matchRepository.getById(matchId) >> TestLoader.generateFinishedMatch()

        def e = thrown(MatchAlreadyFinishedException)
        e.getMessage() == "This match is already finished."
    }

    def "Should get match by ID"() {
        given:
        Long matchId = 1L

        when:
        service.getMatch(matchId)

        then:
        1 * matchRepository.getById(matchId) >> TestLoader.generateMatch()
        2 * movieRepository.getById(_ as Long) >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()]
    }

    def "Should list matches"() {
        given:
        String authToken = "dGVzdDp0ZXN0"

        when:
        service.listMatches(authToken)

        then:
        1 * authenticationService.getUserName(authToken) >> "test"
        1 * userRepository.findByUserName("test") >> TestLoader.generatePersistedUser()
        1 * matchRepository.listByUSer(_ as User) >> TestLoader.generateMatches()
        2 * movieRepository.getById(_ as Long) >>> [TestLoader.generateMovieA(), TestLoader.generateMovieB()]
    }
}
