//package com.memoryGame.MemoryGameOds.controller;
//
//import com.memoryGame.MemoryGameOds.DTO.CardResponseDTO;
//import com.memoryGame.MemoryGameOds.repository.CardRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@RestController
//@RequestMapping("card")
//public class CardControlle {
//    @Autowired
//    private CardRepository cardRepository;
//
//    @GetMapping
//    public List<CardResponseDTO> getAll() {
//        List<CardResponseDTO> pairedList = cardRepository.findAll().stream()
//                .flatMap(card -> List.of(new CardResponseDTO(card), new CardResponseDTO(card)).stream())
//                .toList();
//
//        List<CardResponseDTO> shuffledList = new ArrayList<>(pairedList);
//        Collections.shuffle(shuffledList);
//
//        return shuffledList;
//    }
//}
