package com.memoryGame.MemoryGameOds.controller;

import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
import com.memoryGame.MemoryGameOds.DTO.PlayerResponseDTO;
import com.memoryGame.MemoryGameOds.DTO.PlayerResquestDTO;
import com.memoryGame.MemoryGameOds.model.Game;
import com.memoryGame.MemoryGameOds.model.Player;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {
    @Autowired
    private Game game;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/start-game")
    public void startGame(){
        game.startGame();
    }

    @PostMapping("/end-game")
    public void endGame(){
        game.endGame();
    }

    @PostMapping("/add-player")
    public void addPlayer(@RequestBody PlayerResquestDTO data){
        Player player = new Player(data);
        game.addPlayer(player);
    }

//    @GetMapping("/players")
//    public List<Player> getPlayers() {
//        return playerRepository.findAll();
//    }

    @GetMapping("/load-cards")
    public List<CardResponseDTO> loadCards(){
        return game.loadCards();
    }

    @PostMapping("/calculate-points")
    public Double calculatePoints(@RequestBody Integer timeSeconds){
        return game.calculatePoints(timeSeconds);
    }

    @PostMapping("/update-movements")
    public PlayerResponseDTO updateMovements() {
        Player updatedPlayer = game.updateMovements();
        return new PlayerResponseDTO(updatedPlayer);
    }

    @GetMapping("/game-data")
    public List<PlayerResponseDTO> getGameData() {
        return game.getGameData();
    }
}
