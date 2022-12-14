package io.mbnakaya.imdplay.interactors

import io.mbnakaya.imdplay.datasource.UserRepositoryImpl
import io.mbnakaya.imdplay.domain.User
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class RegisterServiceTest extends Specification {

    RegisterServiceImpl service
    UserRepositoryImpl userRepository = Mock()
    AuthenticationServiceImpl authenticationService = Mock()

    def setup() {
        service = new RegisterServiceImpl(userRepository, authenticationService)
    }

    def "Should registry a new user"() {
        given:
        User user = TestLoader.generateEmptyUser()

        when:
        service.registry(user)

        then:
        1 * userRepository.save(user)
    }

    def "Should get user by auth token"() {
        given:
        String authToken = "dGVzdDp0ZXN0"

        when:
        service.getUser(authToken)

        then:
        1 * authenticationService.getUserName(authToken) >> "test"
        1 * userRepository.findByUserName("test")
    }
}
