package io.mbnakaya.imdplay.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    private Long id;
    private String name;
    private String gender;
    private Integer year;
    private String rated;
    private Double imdbRating;
    private Integer votes;
}
