package io.mbnakaya.imdplay.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private Long score;
}
