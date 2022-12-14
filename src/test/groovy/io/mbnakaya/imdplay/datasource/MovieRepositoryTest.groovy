package io.mbnakaya.imdplay.datasource

import io.mbnakaya.imdplay.domain.Movie
import io.mbnakaya.imdplay.helper.TestLoader
import spock.lang.Specification

class MovieRepositoryTest extends Specification {

    MovieRepositoryImpl repository

    def setup() {
        this.repository = new MovieRepositoryImpl()
    }

    def "Should get movie by ID"(Long matchId) {
        expect:
        repository.getById(matchId) == TestLoader.generateMovieA()

        where:
        matchId << 7L
    }

    def "Should get movie list size"(Integer size) {
        expect:
        repository.getMovieListSize() == size

        where:
        size << 10
    }
}
