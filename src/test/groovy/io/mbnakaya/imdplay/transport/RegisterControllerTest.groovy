package io.mbnakaya.imdplay.transport

import io.mbnakaya.imdplay.helper.TestLoader
import io.mbnakaya.imdplay.interactors.port.RegisterService
import io.mbnakaya.imdplay.transport.controllers.RegisterController
import io.mbnakaya.imdplay.transport.dto.CreateUserDTO
import org.springframework.http.HttpStatus
import spock.lang.Specification

class RegisterControllerTest extends Specification {

    RegisterController controller
    RegisterService service = Mock()

    def setup() {
        controller = new RegisterController(service)
    }

    def "Should registry an user"() {
        given:
        CreateUserDTO requestBody = TestLoader.generateCreateUser()

        when:
        def response = controller.registry(requestBody)

        then:
        1 * service.registry(requestBody.toDomain()) >> TestLoader.generatePersistedUser()
        response.getStatusCode() == HttpStatus.CREATED
    }

    def "Should get user"() {
        given:
        String authToken = "dGVzdDoxMjM0"

        when:
        def response = controller.getUser(authToken)

        then:
        1 * service.getUser(authToken) >> TestLoader.generatePersistedUser()
        response.getStatusCode() == HttpStatus.OK
    }
}
