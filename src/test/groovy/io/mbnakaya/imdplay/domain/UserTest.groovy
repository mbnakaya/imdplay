package io.mbnakaya.imdplay.domain

import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class UserTest extends Specification {

    def "Should add score"() {
        given:
        User user = TestLoader.generateEmptyUser()

        when:
        user.addScore(10L)

        then:
        user.getScore() == 10L
    }
}
