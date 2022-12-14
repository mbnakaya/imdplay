package io.mbnakaya.imdplay.transport.dto;

import io.mbnakaya.imdplay.domain.Login;
import io.mbnakaya.imdplay.domain.User;
import lombok.Data;

@Data
public class LoginDTO {
    private String userName;
    private String password;

    public Login toDomain() {
        return Login.builder()
                .user(User.builder()
                        .userName(this.getUserName())
                        .password(this.getPassword())
                        .build())
                .build();
    }
}
