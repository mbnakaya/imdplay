package io.mbnakaya.imdplay.datasource

import io.mbnakaya.imdplay.datasource.jpa.MatchRepositoryJpa
import io.mbnakaya.imdplay.datasource.po.MatchPO
import io.mbnakaya.imdplay.datasource.po.UserPO
import io.mbnakaya.imdplay.domain.Match
import io.mbnakaya.imdplay.domain.MatchStatus
import io.mbnakaya.imdplay.domain.User
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class MatchRepositoryTest extends Specification {

    MatchRepositoryImpl repository
    MatchRepositoryJpa repositoryJpa = Mock()

    def setup() {
        repository = new MatchRepositoryImpl(repositoryJpa)
    }

    def "Should save a match"() {
        given:
        Match match = TestLoader.generateMatch()
        MatchPO po = MatchPO.toPO(match)

        when:
        repository.save(match)

        then:
        1 * repositoryJpa.save(po) >> TestLoader.generateMatchPO()
    }

    def "Should save a finished match"() {
        given:
        Match match = TestLoader.generateMatch()
        match.setStatus(MatchStatus.FINISHED)

        when:
        repository.save(match)

        then:
        1 * repositoryJpa.save(_ as MatchPO) >> { args -> {
            MatchPO assertable = args.first() as MatchPO
            assert assertable.getMovieA() == null
            assert assertable.getMovieB() == null

            return assertable
        }}
    }

    def "Should get match by ID"() {
        given:
        Long matchId = 0L

        when:
        def assertable = repository.getById(matchId)

        then:
        1 * repositoryJpa.findById(matchId) >> Optional.of(TestLoader.generateMatchPO())
        assertable.class == Match
    }

    def "Should list matches by user"() {
        given:
        User user = TestLoader.generatePersistedUser()

        when:
        repository.listByUSer(user)

        then:
        1 * repositoryJpa.findAllByUser(_ as UserPO) >> { args ->
            UserPO assertable = args.first() as UserPO
            assert assertable == UserPO.toPO(user)

            return Arrays.asList(TestLoader.generateMatchPO())
        }
    }
}
