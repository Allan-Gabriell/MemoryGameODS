//package com.memoryGame.MemoryGameOds.controller;
//
//import com.memoryGame.MemoryGameOds.DTO.PlayerResponseDTO;
//import com.memoryGame.MemoryGameOds.DTO.PlayerResquestDTO;
//import com.memoryGame.MemoryGameOds.model.Player;
//import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("player")
//public class PlayerController {
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @GetMapping
//    public List<PlayerResponseDTO> findAll() {
//        List<PlayerResponseDTO> listPlayers = playerRepository.findAll().stream()
//                .map(PlayerResponseDTO::new)
//                .toList();
//        return listPlayers;
//    }
//
//    @PostMapping
//    public void savePlayer(@RequestBody PlayerResquestDTO data){
//        Player playerData = new Player(data);
//        playerRepository.save(playerData);
//        return;
//    }
//}
