package com.memoryGame.MemoryGameOds;

import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
import com.memoryGame.MemoryGameOds.DTO.PlayerResponseDTO;
import com.memoryGame.MemoryGameOds.model.Card;
import com.memoryGame.MemoryGameOds.model.Game;
import com.memoryGame.MemoryGameOds.model.Player;
import com.memoryGame.MemoryGameOds.model.Score;
import com.memoryGame.MemoryGameOds.repository.CardRepository;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
import com.memoryGame.MemoryGameOds.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTests {

	@Mock
	private PlayerRepository playerRepository;

	@Mock
	private CardRepository cardRepository;

	@Mock
	private ScoreRepository scoreRepository;

	@InjectMocks
	private Game game;

	private Player player;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		player = new Player();
		player.setId(1L);
		player.setName("TestPlayer");
		player.setMovements(0);

		Score score = new Score(0.0, 0, player);
		player.setScore(score);
	}

	@Test
	void startGame_ShouldInitializePlayerAndScore() {
		when(playerRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(player));

		game.startGame();

		assertEquals(24, player.getMovements());
		assertNotNull(player.getScore());
		assertEquals(0.0, player.getScore().getScore());

		verify(playerRepository, times(1)).save(player);
		verify(scoreRepository, times(1)).save(player.getScore());
	}

	@Test
	void addPlayer_ShouldThrowExceptionIfPlayerExists() {
		when(playerRepository.findByName("TestPlayer")).thenReturn(Optional.of(player));

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			game.addPlayer(player);
		});

		assertEquals("Player with name TestPlayer already exists.", exception.getMessage());
	}

	@Test
	void addPlayer_ShouldSaveNewPlayer() {
		when(playerRepository.findByName("NewPlayer")).thenReturn(Optional.empty());

		Player newPlayer = new Player();
		newPlayer.setName("NewPlayer");

		game.addPlayer(newPlayer);

		verify(playerRepository, times(1)).save(newPlayer);
	}

	@Test
	void loadCards_ShouldReturnShuffledPairList() {
		Card card1 = new Card(1L, "Card1", "Desc1", "img1");
		Card card2 = new Card(2L, "Card2", "Desc2", "img2");

		when(cardRepository.findAll()).thenReturn(Arrays.asList(card1, card2));

		List<CardResponseDTO> cards = game.loadCards();

		assertEquals(4, cards.size());
		assertTrue(cards.stream().anyMatch(c -> c.name().equals("Card1")));
		assertTrue(cards.stream().anyMatch(c -> c.name().equals("Card2")));
	}

	@Test
	void calculatePoints_ShouldReturnCorrectMultiplier() {
		assertEquals(60.0, game.calculatePoints(30));
		assertEquals(75.0, game.calculatePoints(50));
		assertEquals(67.5, game.calculatePoints(90));
		assertEquals(100.0, game.calculatePoints(200));
	}

	@Test
	void getRanking_ShouldReturnPlayersSortedByScore() {
		Player p1 = new Player();
		p1.setId(1L);
		p1.setName("A");
		p1.setScore(new Score(50.0, 0, p1));

		Player p2 = new Player();
		p2.setId(2L);
		p2.setName("B");
		p2.setScore(new Score(100.0, 0, p2));

		when(playerRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

		List<PlayerResponseDTO> ranking = game.getRanking();

		assertEquals("B", ranking.get(0).name());
		assertEquals("A", ranking.get(1).name());
	}

	@Test
	void updateLastPlayer_ShouldUpdateNameAndScore() {
		when(playerRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(player));
		when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
		when(playerRepository.save(any(Player.class))).thenReturn(player);

		Player updatedData = new Player();
		updatedData.setName("UpdatedName");

		Score updatedScore = new Score(200.0, 0, updatedData);
		updatedData.setScore(updatedScore);

		PlayerResponseDTO result = game.updateLastPlayer(updatedData);

		assertEquals("UpdatedName", result.name());
		assertEquals(200.0, result.score().getScore());
	}
}

