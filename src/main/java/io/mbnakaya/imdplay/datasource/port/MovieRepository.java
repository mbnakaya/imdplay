package io.mbnakaya.imdplay.datasource.port;

import io.mbnakaya.imdplay.domain.Movie;

public interface MovieRepository {
    Movie getById(Long id);
    Integer getMovieListSize();
}
