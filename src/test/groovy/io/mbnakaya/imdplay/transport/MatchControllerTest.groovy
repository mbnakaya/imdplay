package io.mbnakaya.imdplay.transport

import io.mbnakaya.imdplay.domain.Response
import io.mbnakaya.imdplay.helper.TestLoader
import io.mbnakaya.imdplay.interactors.port.MatchService
import io.mbnakaya.imdplay.transport.controllers.MatchController
import org.springframework.http.HttpStatus
import spock.lang.Specification

class MatchControllerTest extends Specification {

    MatchController controller
    MatchService service = Mock()

    def setup() {
        controller = new MatchController(service)
    }

    def "Should start a match"() {
        given:
        String authToken = "dGVzdDoxMjM0"

        when:
        def response = controller.startMatch(authToken)

        then:
        1 * service.startMatch(authToken) >> TestLoader.generateMatch()
        response.getStatusCode() == HttpStatus.CREATED
    }

    def "Should respond the current quiz"() {
        given:
        Long matchId = 0L
        String response = "A"

        when:
        def result = controller.respond(matchId, response)

        then:
        1 * service.processResponse(matchId, Response.A) >> TestLoader.generateMatchWrightAnswer()
        result.getStatusCode() == HttpStatus.OK
    }

    def "Should finish the match"() {
        given:
        Long matchId = 0L

        when:
        def result = controller.finishMatch(matchId)

        then:
        1 * service.finishMatch(matchId) >> TestLoader.generateFinishedMatch()
        result.getStatusCode() == HttpStatus.OK
    }

    def "Should get a match"() {
        given:
        Long matchId = 0L

        when:
        def response = controller.getMatch(matchId)

        then:
        1 * service.getMatch(matchId) >> TestLoader.generateMatchWrightAnswer()
        response.getStatusCode() == HttpStatus.OK
    }

    def "Should list the matches of a user"() {
        given:
        String authToken = "dGVzdDoxMjM0"

        when:
        def response = controller.listMatches(authToken)

        then:
        1 * service.listMatches(authToken) >> TestLoader.generateMatches()
        response.getStatusCode() == HttpStatus.OK
    }
}
