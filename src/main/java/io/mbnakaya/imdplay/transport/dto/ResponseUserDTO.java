package io.mbnakaya.imdplay.transport.dto;

import io.mbnakaya.imdplay.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserDTO {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private Long score;

    public static ResponseUserDTO fromDomain(User user) {
        return ResponseUserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUserName())
                .email(user.getEmail())
                .score(user.getScore())
                .build();
    }
}
