package io.mbnakaya.imdplay.transport.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String fullName;
    private String username;
    private String email;
    private String password;
}
