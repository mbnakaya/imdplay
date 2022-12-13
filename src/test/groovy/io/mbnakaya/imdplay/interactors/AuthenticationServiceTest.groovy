package io.mbnakaya.imdplay.interactors

import io.mbnakaya.imdplay.datasource.UserRepositoryImpl
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class AuthenticationServiceTest extends Specification {

    AuthenticationServiceImpl service
    UserRepositoryImpl userRepository = Mock()

    def setup() {
        service = new AuthenticationServiceImpl(userRepository)
    }

    def "Should get user name"() {
        when:
        String userName = service.getUserName("dGVzdDp0ZXN0")

        then:
        userName == "test"
    }

    def "Should authenticate an user"() {
        when:
        Boolean result = service.authenticate("dGVzdDp0ZXN0")

        then:
        1 * userRepository.login("test", "test") >> TestLoader.generateEmptyUser()
        result == true
    }
}
