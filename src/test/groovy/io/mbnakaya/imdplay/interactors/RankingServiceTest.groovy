package io.mbnakaya.imdplay.interactors

import io.mbnakaya.imdplay.datasource.UserRepositoryImpl
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class RankingServiceTest extends Specification {

    RankingServiceImpl service
    UserRepositoryImpl userRepository = Mock()

    def setup() {
        service = new RankingServiceImpl(userRepository)
    }

    def "Should get users ranking"() {
        given:
        Integer top = 10

        when:
        service.getRanking(top)

        then:
        1 * userRepository.getRanking(top) >> Arrays.asList(TestLoader.generatePersistedUser())
    }
}
