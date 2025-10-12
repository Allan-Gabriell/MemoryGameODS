package com.memoryGame.MemoryGameOds.model;

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

    private List<Player> players = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    private boolean isGameActive = false;

    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    public void loadCards(List<Card> cardList){
        cards.clear();
        cards.addAll(cardList);
        Collections.shuffle(cards);
    }
}
