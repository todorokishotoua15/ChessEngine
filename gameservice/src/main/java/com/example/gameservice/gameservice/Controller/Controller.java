package com.example.gameservice.gameservice.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.gameservice.gameservice.Model.Game;
import com.example.gameservice.gameservice.Model.GameRequest;
import com.example.gameservice.gameservice.Repository.GameRepository;
import com.example.gameservice.gameservice.Service.GameService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/gameservice")
public class Controller {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepo;

    @GetMapping("/loadGame")
    public ResponseEntity<Game> loadGame(@RequestBody GameRequest req) {
        return ResponseEntity.ok(gameRepo.findGameByUserId(req.getUser_email(), req.getGame_id()));
    }

    @GetMapping("/loadAllGames")
    public ResponseEntity<Collection<Game>> loadAllGames(@RequestBody GameRequest req) {
        return ResponseEntity.ok(gameRepo.findAllGames(req.getUser_email()));
    }

    @GetMapping("/loadUnfinishedGames")
    public ResponseEntity<Collection<Game>> loadUnfinishedGames(@RequestBody GameRequest req) {
        return ResponseEntity.ok(gameRepo.findUnfinishedGames(req.getUser_email()));
    }
}
