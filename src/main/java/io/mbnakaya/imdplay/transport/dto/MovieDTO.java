package io.mbnakaya.imdplay.transport.dto;

import io.mbnakaya.imdplay.domain.Movie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDTO {
    private String tittle;
    private String genre;
    private Integer year;
    private String director;
    private String rated;

    public static MovieDTO fromDomain(Movie movie) {
        return MovieDTO.builder()
                .tittle(movie.getTittle())
                .genre(movie.getGenre())
                .year(movie.getYear())
                .director(movie.getDirector())
                .rated(movie.getRated())
                .build();
    }
}
