package com.memoryGame.MemoryGameOds.DTO;

import com.memoryGame.MemoryGameOds.model.Player;
import com.memoryGame.MemoryGameOds.model.Score;

public record PlayerResponseDTO(Long id, String name, String nacionality, Integer movements, Score score) {
    public PlayerResponseDTO(Player player){
        this(player.getId(), player.getName(), player.getNacionality(), player.getMovements(), player.getScore());
    }
}
