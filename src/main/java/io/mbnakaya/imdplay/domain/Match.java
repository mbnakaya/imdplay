package io.mbnakaya.imdplay.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private MatchStatus status;
    private List<String> answered;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Match generateNewOne(User user) {
        return Match.builder()
                .user(user)
                .chances(3)
                .points(0)
                .result("")
                .status(MatchStatus.CREATED)
                .answered(Collections.emptyList())
                .build();
    }

    public void reduceChance() {
        this.chances -= 1;
    }

    public void addAnswered(Long id) {
        List<String> list = new ArrayList<>(this.getAnswered());
        list.add(id.toString());
        this.setAnswered(list);
    }

    public void positiveResult() {
        this.points += 1;
        this.result += "O";
    }

    public void negativeResult() {
        this.result += "X";
    }
}
