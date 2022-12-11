package io.mbnakaya.imdplay.transport.dto;

import lombok.Data;

@Data
public class ResponseUserDTO {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private Long score;
}
