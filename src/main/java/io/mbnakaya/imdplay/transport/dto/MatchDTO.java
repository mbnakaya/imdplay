package io.mbnakaya.imdplay.transport.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.Movie;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MatchDTO {
    private Long id;
    private ResponseUserDTO user;
    private Integer chances;
    private MovieDTO movieA;
    private MovieDTO movieB;
    private Integer points;
    private String result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;

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
                .movieA(MovieDTO.fromDomain(match.getMovieA()))
                .movieB(MovieDTO.fromDomain(match.getMovieB()))
                .points(match.getPoints())
                .result(match.getResult())
                .createdAt(match.getCreatedAt())
                .updatedAt(match.getUpdatedAt())
                .build();
    }
}
