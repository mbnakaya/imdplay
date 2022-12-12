package io.mbnakaya.imdplay.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Match {
    private Long id;
    private User user;
    private Integer chances;
    private Movie movieA;
    private Movie movieB;
    private Integer points;
    private String result;
    private List<Long> answered;

    public static Match generateNewOne(User user) {
        return Match.builder()
                .user(user)
                .chances(3)
                .points(0)
                .result("")
                .answered(Collections.emptyList())
                .build();
    }
}
