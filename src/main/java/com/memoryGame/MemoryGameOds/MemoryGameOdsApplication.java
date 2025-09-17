package com.memoryGame.MemoryGameOds;

import com.memoryGame.MemoryGameOds.game.Game;
import com.memoryGame.MemoryGameOds.repository.CardRepository;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Depois excluir quando for mexer com os bancos
public class MemoryGameOdsApplication implements CommandLineRunner {
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private CardRepository cardRepository;


	public static void main(String[] args) {
		SpringApplication.run(MemoryGameOdsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Game game = new Game(playerRepository,  cardRepository);
		game.newPlayer();
		game.loadPlayer();
//		game.newCard();
//		game.loadCards();

		System.exit(0);
	}
}
