package io.mbnakaya.imdplay.datasource.po;

import io.mbnakaya.imdplay.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Long score;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany
    private List<MatchPO> matches;

    public static UserPO toPO(User user) {
        return UserPO.builder()
                .fullName(user.getFullName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .score(user.getScore())
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(this.getId())
                .fullName(this.getFullName())
                .userName(this.getUserName())
                .email(this.getEmail())
                .password(this.getPassword())
                .score(this.getScore())
                .build();
    }
}
