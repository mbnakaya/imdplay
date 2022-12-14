package io.mbnakaya.imdplay.transport.dto;

import io.mbnakaya.imdplay.domain.Login;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLoginDTO {
    private ResponseUserDTO user;
    private String authToken;

    public static ResponseLoginDTO fromDomain(Login login) {
        return ResponseLoginDTO.builder()
                .user(ResponseUserDTO.fromDomain(login.getUser()))
                .authToken(login.getAuthToken())
                .build();
    }
}
