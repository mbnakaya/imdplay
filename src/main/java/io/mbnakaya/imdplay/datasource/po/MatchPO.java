package io.mbnakaya.imdplay.datasource.po;

import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.MatchStatus;
import io.mbnakaya.imdplay.domain.Movie;
import io.mbnakaya.imdplay.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Entity
@Table(name = "matches")
@AllArgsConstructor
@NoArgsConstructor
public class MatchPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserPO user;
    @Column(nullable = false)
    private Integer chances;
    @Column(name = "movie_a")
    private Long movieA;
    @Column(name = "movie_b")
    private Long movieB;
    @Column(nullable = false)
    private Integer points;
    @Column(nullable = false)
    private String result;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
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
                .status(match.getStatus())
                .answered(String.join(",", match.getAnswered()))
                .build();
    }

    public static List<Match> toDomainList(List<MatchPO> matches) {
        return matches.stream().map(MatchPO::toDomain).collect(Collectors.toList());
    }

    public Match toDomain() {
        return Match.builder()
                .id(this.getId())
                .user(User.builder()
                        .id(this.getUser().getId())
                        .fullName(this.getUser().getFullName())
                        .userName(this.getUser().getUserName())
                        .email(this.getUser().getEmail())
                        .password(this.getUser().getPassword())
                        .score(this.getUser().getScore())
                        .build())
                .chances(this.getChances())
                .movieA(Movie.builder()
                        .id(this.getMovieA())
                        .build())
                .movieB(Movie.builder()
                        .id(this.getMovieB())
                        .build())
                .points(this.getPoints())
                .result(this.getResult())
                .status(this.status)
                .answered(Arrays.asList(this.getAnswered()))
                .build();
    }
}
