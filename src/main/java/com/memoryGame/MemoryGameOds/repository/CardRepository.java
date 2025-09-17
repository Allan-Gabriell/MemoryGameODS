package com.memoryGame.MemoryGameOds.repository;

import com.memoryGame.MemoryGameOds.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
