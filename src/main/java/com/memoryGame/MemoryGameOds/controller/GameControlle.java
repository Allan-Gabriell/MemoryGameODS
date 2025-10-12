package com.memoryGame.MemoryGameOds.controller;

import com.memoryGame.MemoryGameOds.DTO.PlayerResquestDTO;
import com.memoryGame.MemoryGameOds.model.Game;
import com.memoryGame.MemoryGameOds.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
