package io.mbnakaya.imdplay.datasource.po;

import io.mbnakaya.imdplay.domain.Match;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "matches")
@AllArgsConstructor
@NoArgsConstructor
public class MatchPO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserPO user;
    @Column(nullable = false)
    private Integer chances;
    @Column(name = "movie_a", nullable = false)
    private Long movieA;
    @Column(name = "movie_b",nullable = false)
    private Long movieB;
    @Column(nullable = false)
    private Integer points;
    @Column(nullable = false)
    private String result;
    @Column(nullable = false)
    private String answered;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static MatchPO toPO(Match match) {
        return MatchPO.builder()
                .id(match.getId())
                .user(UserPO.builder()
                        .id(match.getUser().getId())
                        .fullName(match.getUser().getFullName())
                        .userName(match.getUser().getUserName())
                        .email(match.getUser().getEmail())
                        .password(match.getUser().getPassword())
                        .score(match.getUser().getScore())
                        .build())
                .chances(match.getChances())
                .movieA(match.getMovieA().getId())
                .movieB(match.getMovieB().getId())
                .points(match.getPoints())
                .result(match.getResult())
                .answered(String.join(",", match.getAnswered()))
                .build();
    }
}
