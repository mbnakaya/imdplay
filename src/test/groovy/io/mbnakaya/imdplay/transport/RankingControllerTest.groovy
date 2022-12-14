package io.mbnakaya.imdplay.transport

import io.mbnakaya.imdplay.helper.TestLoader
import io.mbnakaya.imdplay.interactors.port.RankingService
import io.mbnakaya.imdplay.transport.controllers.RankingController
import io.mbnakaya.imdplay.transport.dto.ResponseUserDTO
import org.springframework.http.HttpStatus
import spock.lang.Specification

class RankingControllerTest extends Specification {

    RankingController controller
    RankingService service = Mock()

    def setup() {
        this.controller = new RankingController(service)
    }

    def "Should get the current user ranking"() {
        given:
        Integer top = 10

        when:
        def response = controller.getRanking(top)

        then:
        1 * service.getRanking(top) >> Arrays.asList(TestLoader.generatePersistedUser())
        response.getStatusCode() == HttpStatus.OK
    }
}
