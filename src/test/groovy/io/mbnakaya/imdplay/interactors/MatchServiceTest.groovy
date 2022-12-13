package io.mbnakaya.imdplay.interactors

import io.mbnakaya.imdplay.datasource.MatchRepositoryImpl
import io.mbnakaya.imdplay.datasource.MovieRepositoryImpl
import io.mbnakaya.imdplay.datasource.UserRepositoryImpl
import io.mbnakaya.imdplay.domain.Match
import io.mbnakaya.imdplay.domain.MatchStatus
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
}
