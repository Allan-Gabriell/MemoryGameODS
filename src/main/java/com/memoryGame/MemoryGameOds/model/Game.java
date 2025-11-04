package com.memoryGame.MemoryGameOds.model;

import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
import com.memoryGame.MemoryGameOds.DTO.PlayerResponseDTO;
import com.memoryGame.MemoryGameOds.repository.CardRepository;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
import com.memoryGame.MemoryGameOds.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class Game {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    private List<Player> players;
    private boolean isGameActive = false;
    private int totalTimeSeconds = 0;

    public void startGame() {
        Player player = playerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));

        player.setMovements(24);
        if (player.getScore() != null) {
            player.getScore().setScore(0.0);
            scoreRepository.save(player.getScore());
        } else {
            Score score = new Score(0.0, 0);
            score.setPlayer(player);
            player.setScore(score);
            scoreRepository.save(score);
        }
        playerRepository.save(player);

        totalTimeSeconds = 0;
        isGameActive = true;
    }

    public void endGame() {
        Player player = playerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));

        if(player.getScore() != null) {
            player.getScore().setTimeSeconds(totalTimeSeconds);
            scoreRepository.save(player.getScore());
        }

        isGameActive = false;
        System.exit(0);
    }

    public void addPlayer(Player player){
        if (playerRepository.findByName(player.getName()).isPresent()) {
            throw new RuntimeException("Player with name " + player.getName() + " already exists.");
        }
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

    public Double calculatePoints(int timeSeconds){
        Double multiplier;

        if(timeSeconds <= 30){
            multiplier = 2.0;
        } else if(timeSeconds <= 60){
            multiplier = 1.5;
        } else if(timeSeconds <= 90){
            multiplier = 0.75;
        } else {
            multiplier = 0.5;
        }

        Double score = (timeSeconds * multiplier);
        System.out.println("Score : " + score);
        return score;
    }

    public void updateMovements(boolean isValid, int timeSeconds) {
        totalTimeSeconds += timeSeconds;
        Player player = playerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));

        System.out.println("Player : " + player.getName() + player.getMovements());

        if (player.getMovements() > 0) {
            player.setMovements(player.getMovements() - 1);

            if (isValid) {
                Double calculatedScore = calculatePoints(timeSeconds);
                Score score = player.getScore();
                if (score == null) {
                    score = new Score();
                    player.setScore(score);
                }
                score.setScore(score.getScore() + calculatedScore);
                scoreRepository.save(score);
            }

            playerRepository.save(player);
        } else {
            endGame();
        }

    }

    public PlayerResponseDTO getGameData() {
        return playerRepository.findTopByOrderByIdDesc()
                .map(PlayerResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));
    }



    public List<PlayerResponseDTO> getRanking(){
        players = playerRepository.findAll();

        return players.stream()
                .sorted(Comparator.comparing((Player p) -> p.getScore().getScore()).reversed())
                .map(PlayerResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PlayerResponseDTO updateLastPlayer(Player updatedData) {
        Player lastPlayer = playerRepository.findById(getGameData().id())
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));

        lastPlayer.setName(updatedData.getName());
        if (updatedData.getScore() != null) {
            lastPlayer.setScore(updatedData.getScore());
        }

        Player savedPlayer = playerRepository.save(lastPlayer);

        return new PlayerResponseDTO(savedPlayer);
    }

}