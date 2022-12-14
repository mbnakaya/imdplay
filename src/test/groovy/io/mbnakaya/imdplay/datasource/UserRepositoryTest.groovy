package io.mbnakaya.imdplay.datasource

import io.mbnakaya.imdplay.datasource.jpa.UserRepositoryJpa
import io.mbnakaya.imdplay.datasource.po.UserPO
import io.mbnakaya.imdplay.domain.User
import io.mbnakaya.imdplay.domain.exceptions.UnauthorizedException
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class UserRepositoryTest extends Specification {

    UserRepositoryImpl repository
    UserRepositoryJpa repositoryJpa = Mock()

    def setup() {
        this.repository = new UserRepositoryImpl(repositoryJpa)
    }

    def "Should save an user"() {
        given:
        User user = TestLoader.generateEmptyUser()

        when:
        repository.save(user)

        then:
        1 * repositoryJpa.save(_ as UserPO) >> UserPO.toPO(TestLoader.generatePersistedUser())
    }

    def "Should login user"() {
        given:
        String userName = "test"
        String password = "1234"

        when:
        repository.login(userName, password)

        then:
        1 * repositoryJpa.findByUserNameAndPassword(userName, password) >> UserPO.toPO(TestLoader.generatePersistedUser())
    }

    def "Should fail login user"() {
        given:
        String userName = "test"
        String password = "1234"

        when:
        repository.login(userName, password)

        then:
        1 * repositoryJpa.findByUserNameAndPassword(userName, password) >> null

        def error = thrown(UnauthorizedException)
        error.getMessage() == "Invalid user and/or password."
    }

    def "Should find user by its user name"() {
        given:
        String userName = "test"

        when:
        repository.findByUserName(userName)

        then:
        1 * repositoryJpa.findByUserName(userName) >> UserPO.toPO(TestLoader.generatePersistedUser())
    }

    def "Should get ranking"() {
        given:
        Integer top = 10

        when:
        repository.getRanking(top)

        then:
        1 * repositoryJpa.getRanking(top) >> Arrays.asList(UserPO.toPO(TestLoader.generatePersistedUser()))
    }
}
