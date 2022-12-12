package io.mbnakaya.imdplay.transport.controllers;

import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.interactors.port.MatchService;
import io.mbnakaya.imdplay.transport.dto.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/match")
public class MatchController {

    @Autowired
    private MatchService service;

    @PostMapping
    public ResponseEntity<MatchDTO> startMatch(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        Match result = service.startMatch(authToken);
        return new ResponseEntity<>(MatchDTO.fromDomain(result), HttpStatus.CREATED);
    }
}
