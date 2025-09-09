package com.memoryGame.MemoryGameOds.game;

import com.memoryGame.MemoryGameOds.model.Card;
import com.memoryGame.MemoryGameOds.model.Player;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;

import java.util.*;

public class Game {
    private Player player;
    private List<Card> cards = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private PlayerRepository playerRepository;

    public Game(PlayerRepository playerRepository) {this.playerRepository = playerRepository;}

    public void newPlayer(){
        System.out.println("Informe o nome do player : ");
        var name = scanner.nextLine();
        System.out.println("Informe a nacionalidade do player : ");
        var nacionality = scanner.nextLine();
        player = new Player(name, nacionality);

        playerRepository.save(player);

        System.out.println(player);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
}
