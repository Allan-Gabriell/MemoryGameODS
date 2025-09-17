package com.memoryGame.MemoryGameOds.DTO;

import com.memoryGame.MemoryGameOds.model.Card;

public record CardResponseDTO(Long id, String name, String description, String imgUrl) {
    public CardResponseDTO(Card card) {
        this(card.getId(), card.getName(), card.getDescription(), card.getImgUrl());
    }
}
