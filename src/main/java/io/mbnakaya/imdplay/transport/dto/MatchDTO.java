package io.mbnakaya.imdplay.transport.dto;

import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.Movie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchDTO {
    private Long id;
    private ResponseUserDTO user;
    private Integer chances;
    private Movie movieA;
    private Movie movieB;
    private Integer points;
    private String result;

    public static MatchDTO fromDomain(Match match) {
        return MatchDTO.builder()
                .id(match.getId())
                .user(ResponseUserDTO.builder()
                        .id(match.getUser().getId())
                        .fullName(match.getUser().getFullName())
                        .username(match.getUser().getUserName())
                        .email(match.getUser().getEmail())
                        .score(match.getUser().getScore())
                        .build())
                .chances(match.getChances())
                .movieA(match.getMovieA())
                .movieB(match.getMovieB())
                .points(match.getPoints())
                .result(match.getResult())
                .build();
    }
}
