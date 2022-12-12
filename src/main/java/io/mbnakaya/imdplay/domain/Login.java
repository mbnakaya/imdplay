package io.mbnakaya.imdplay.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Login {
    private User user;
    private String authToken;
}
