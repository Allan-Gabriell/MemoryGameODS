package com.memoryGame.MemoryGameOds.controller;

import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
import com.memoryGame.MemoryGameOds.DTO.PlayerResquestDTO;
import com.memoryGame.MemoryGameOds.model.Game;
import com.memoryGame.MemoryGameOds.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameControlle {
    @Autowired
    private Game game;

    @PostMapping("/add-player")
    public void addPlayer(@RequestBody PlayerResquestDTO data){
        Player player = new Player(data);
        game.addPlayer(player);
    }

    @GetMapping("/load-cards")
    public List<CardResponseDTO> loadCards(){
        return game.loadCards();
    }
}
