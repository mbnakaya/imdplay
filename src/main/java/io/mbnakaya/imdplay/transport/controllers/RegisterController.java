package io.mbnakaya.imdplay.transport.controllers;

import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.RegisterService;
import io.mbnakaya.imdplay.transport.dto.CreateUserDTO;
import io.mbnakaya.imdplay.transport.dto.ResponseUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping
    public ResponseEntity<ResponseUserDTO> registry(@RequestBody CreateUserDTO body) {
        User user = service.registry(body.toDomain());
        return new ResponseEntity<>(
                ResponseUserDTO.fromDomain(user),
                HttpStatus.CREATED
        );
    }
}
