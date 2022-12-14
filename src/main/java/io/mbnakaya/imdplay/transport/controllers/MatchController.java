package io.mbnakaya.imdplay.transport.controllers;

import io.mbnakaya.imdplay.domain.Match;
import io.mbnakaya.imdplay.domain.Response;
import io.mbnakaya.imdplay.interactors.port.MatchService;
import io.mbnakaya.imdplay.transport.dto.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/match")
public class MatchController {

    private final MatchService service;

    public MatchController(MatchService bean) {
        this.service = bean;
    }

    @PostMapping
    public ResponseEntity<MatchDTO> startMatch(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        Match result = service.startMatch(authToken);
        return new ResponseEntity<>(MatchDTO.fromDomain(result), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MatchDTO> respond(@PathVariable Long id, @RequestParam String response) {
        Match result = service.processResponse(id, Response.valueOf(response));
        return new ResponseEntity<>(MatchDTO.fromDomain(result), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MatchDTO> finishMatch(@PathVariable Long id) {
        Match result = service.finishMatch(id);
        return new ResponseEntity<>(MatchDTO.fromDomain(result), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatch(@PathVariable Long id) {
        Match result = service.getMatch(id);
        return new ResponseEntity<>(MatchDTO.fromDomain(result), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> listMatches(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        List<Match> result = service.listMatches(authToken);
        return new ResponseEntity<>(MatchDTO.fromDomain(result), HttpStatus.OK);
    }
}
