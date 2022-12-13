package io.mbnakaya.imdplay.interactors

import io.mbnakaya.imdplay.datasource.UserRepositoryImpl
import io.mbnakaya.imdplay.domain.Login
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class LoginServiceTest extends Specification {

    LoginServiceImpl service
    UserRepositoryImpl userRepository = Mock()

    def setup() {
        service = new LoginServiceImpl(userRepository)
    }

    def "Should authenticate user"() {
        given:
        Login login = TestLoader.generateBasicLogin()

        when:
        Login result = service.authenticate(login)

        then:
        1 * userRepository.login(_ as String, _ as String) >> TestLoader.generatePersistedUser()
        result.getUser().getId() == 1L
        result.getAuthToken() != null
    }
}
