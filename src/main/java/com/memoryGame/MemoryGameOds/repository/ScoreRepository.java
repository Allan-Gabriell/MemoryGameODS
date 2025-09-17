package com.memoryGame.MemoryGameOds.repository;

import com.memoryGame.MemoryGameOds.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score,Long> {
}
