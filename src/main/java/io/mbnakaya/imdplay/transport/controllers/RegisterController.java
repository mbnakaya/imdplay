package io.mbnakaya.imdplay.transport.controllers;

import io.mbnakaya.imdplay.transport.dto.CreateUserDTO;
import io.mbnakaya.imdplay.transport.dto.ResponseUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/register")
public class RegisterController {

    @PostMapping
    public ResponseEntity<ResponseUserDTO> register(@RequestBody CreateUserDTO body) {
        return new ResponseEntity<>(new ResponseUserDTO(), HttpStatus.CREATED);
    }
}
