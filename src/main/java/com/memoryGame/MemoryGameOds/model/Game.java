package com.memoryGame.MemoryGameOds.model;

import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
import com.memoryGame.MemoryGameOds.repository.CardRepository;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Game {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CardRepository cardRepository;

    private List<Player> players = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    private boolean isGameActive = false;

    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    public List<CardResponseDTO> loadCards(){
        List<CardResponseDTO> pairedList = cardRepository.findAll().stream()
                .flatMap(card -> List.of(new CardResponseDTO(card), new CardResponseDTO(card)).stream())
                .toList();

        List<CardResponseDTO> shuffledList = new ArrayList<>(pairedList);
        Collections.shuffle(shuffledList);

        return shuffledList;
    }
}
