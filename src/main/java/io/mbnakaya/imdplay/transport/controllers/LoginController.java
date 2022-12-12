package io.mbnakaya.imdplay.transport.controllers;

import io.mbnakaya.imdplay.domain.Login;
import io.mbnakaya.imdplay.interactors.port.LoginService;
import io.mbnakaya.imdplay.transport.dto.LoginDTO;
import io.mbnakaya.imdplay.transport.dto.ResponseLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody LoginDTO body) {
        Login result = service.authenticate(body.toDomain());
        return new ResponseEntity<>(
                ResponseLoginDTO.fromDomain(result),
                HttpStatus.OK);
    }
}
