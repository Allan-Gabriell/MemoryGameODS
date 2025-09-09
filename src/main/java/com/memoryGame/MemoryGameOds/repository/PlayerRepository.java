package com.memoryGame.MemoryGameOds.repository;

import com.memoryGame.MemoryGameOds.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
