package io.mbnakaya.imdplay.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    private Long id;
    private String tittle;
    private String genre;
    private Integer year;
    private String director;
    private String rated;
    private Double imdbRating;
    private Integer votes;
}
