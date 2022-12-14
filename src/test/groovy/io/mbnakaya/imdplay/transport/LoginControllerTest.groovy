package io.mbnakaya.imdplay.transport

import io.mbnakaya.imdplay.domain.Login
import io.mbnakaya.imdplay.helper.TestLoader
import io.mbnakaya.imdplay.interactors.port.LoginService
import io.mbnakaya.imdplay.transport.controllers.LoginController
import io.mbnakaya.imdplay.transport.dto.LoginDTO
import spock.lang.Specification

class LoginControllerTest extends Specification {

    LoginController controller
    LoginService service = Mock()

    def setup() {
        this.controller = new LoginController(service)
    }

    def "Should login"() {
        given:
        LoginDTO requestBody = new LoginDTO()
        requestBody.setUserName("test")
        requestBody.setPassword("1234")

        when:
        controller.login(requestBody)

        then:
        1 * service.authenticate(requestBody.toDomain()) >>
                Login.builder()
                    .user(TestLoader.generatePersistedUser())
                    .authToken("dGVzdDoxMjM0")
                    .build()
    }
}
