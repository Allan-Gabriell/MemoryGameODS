package com.memoryGame.MemoryGameOds.model;

import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
import com.memoryGame.MemoryGameOds.DTO.PlayerResponseDTO;
import com.memoryGame.MemoryGameOds.repository.CardRepository;
import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;


@Service
public class Game {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CardRepository cardRepository;

    private List<Player> players;
    private boolean isGameActive = false;

    public void startGame(){isGameActive = true;}

    public void endGame(){isGameActive = false;}

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

    public Player updateMovements() {
        Player player = playerRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));

        player.setMovements(player.getMovements() - 1);

        return playerRepository.save(player);
    }

    public PlayerResponseDTO getGameData() {
        // Retorna o último jogador cadastrado no banco de dados
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
        // Busca o último jogador cadastrado
        Player lastPlayer = playerRepository.findById(getGameData().id())
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado."));

        // Atualiza os campos que vieram do front-end
        lastPlayer.setName(updatedData.getName());
        if (updatedData.getScore() != null) {
            lastPlayer.setScore(updatedData.getScore());
        }
        // Adicione outros campos que quiser atualizar, mas sem mexer em movimentos por enquanto

        // Salva o jogador atualizado no banco
        Player savedPlayer = playerRepository.save(lastPlayer);

        return new PlayerResponseDTO(savedPlayer);
    }

}
