package io.mbnakaya.imdplay.transport.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.MatchStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class MatchDTO {
    private Long id;
    private ResponseUserDTO user;
    private Integer chances;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MovieDTO movieA;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MovieDTO movieB;
    private Integer points;
    private String result;
    private MatchStatus status;
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
                .status(match.getStatus())
                .createdAt(match.getCreatedAt())
                .updatedAt(match.getUpdatedAt())
                .build();
    }

    public static List<MatchDTO> fromDomain(List<Match> matches) {
        return matches.stream().map(MatchDTO::fromDomain).collect(Collectors.toList());
    }
}
