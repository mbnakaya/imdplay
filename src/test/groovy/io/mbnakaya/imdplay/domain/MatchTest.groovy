package io.mbnakaya.imdplay.domain

import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class MatchTest extends Specification {

    def "Should generate new match"() {
        given:
        User user = TestLoader.generateEmptyUser()
        when:
        Match match = Match.generateNewOne(user)

        then:
        match.getId() == null
        match.getUser() == user
        match.getChances() == 3
        match.getAnswered() == new ArrayList()
        match.getPoints() == 0
        match.getStatus() == MatchStatus.CREATED
        match.getMovieA() == null
        match.getMovieB() == null
    }

    def "Should reduce chance"() {
        given:
        Match match = Match.generateNewOne(TestLoader.generateEmptyUser())

        when:
        match.reduceChance()

        then:
        match.getChances() == 2
    }

    def "Should add answered"() {
        given:
        Match match = Match.generateNewOne(TestLoader.generateEmptyUser())

        when:
        match.addAnswered(1L)

        then:
        match.getAnswered() == ["1"]
    }

    def "Should perform positive result"() {
        given:
        Match match = Match.generateNewOne(TestLoader.generateEmptyUser())

        when:
        match.positiveResult()

        then:
        match.getResult() == "O"
        match.getPoints() == 1
    }

    def "Should perform negative result"() {
        given:
        Match match = Match.generateNewOne(TestLoader.generateEmptyUser())

        when:
        match.negativeResult()

        then:
        match.getResult() == "X"
        match.getPoints() == 0
    }
}
