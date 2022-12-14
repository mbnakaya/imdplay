package io.mbnakaya.imdplay.transport.controllers;

import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.RankingService;
import io.mbnakaya.imdplay.transport.dto.ResponseUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/ranking")
public class RankingController {

    private final RankingService service;

    public RankingController(RankingService bean) {
        this.service = bean;
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getRanking(@RequestParam Integer top) {
        List<User> ranking = service.getRanking(top);
        return new ResponseEntity<>(ranking.stream().map(ResponseUserDTO::fromDomain).toList(), HttpStatus.OK);
    }
}
