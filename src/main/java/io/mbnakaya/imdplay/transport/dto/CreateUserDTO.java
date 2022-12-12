package io.mbnakaya.imdplay.transport.dto;

import io.mbnakaya.imdplay.domain.User;
import lombok.Data;

@Data
public class CreateUserDTO {
    private String fullName;
    private String username;
    private String email;
    private String password;

    public User toDomain() {
        return User.builder()
                .fullName(this.getFullName())
                .userName(this.getUsername())
                .email(this.getEmail())
                .password(this.getPassword())
                .score(0L)
                .build();
    }
}
