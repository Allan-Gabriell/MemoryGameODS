//package com.memoryGame.MemoryGameOds.game;
//
//import com.memoryGame.MemoryGameOds.model.Card;
//import com.memoryGame.MemoryGameOds.model.Player;
//import com.memoryGame.MemoryGameOds.model.Score;
//import com.memoryGame.MemoryGameOds.repository.CardRepository;
//import com.memoryGame.MemoryGameOds.repository.PlayerRepository;
//
//import java.util.*;
//
//public class Game {
//    private Player player;
//    private List<Card> cards = new ArrayList<>();
//    private Scanner scanner = new Scanner(System.in);
//    private PlayerRepository playerRepository;
//    private CardRepository cardRepository;
//
//
//    public Game(PlayerRepository playerRepository, CardRepository cardRepository) {
//        this.playerRepository = playerRepository;
//        this.cardRepository = cardRepository;
//    }
//
//    public void newPlayer(){
//        System.out.println("Informe o nome do player : ");
//        var name = scanner.nextLine();
//        System.out.println("Informe a nacionalidade do player : ");
//        var nacionality = scanner.nextLine();
//        player = new Player(name, nacionality);
//
//        Score score = new Score(150, 246);
//        player.setScore(score);
//
//        score.setPlayer(player);
//        playerRepository.save(player);
//    }
//
//    public void loadPlayer(){
//        List<Player> players = playerRepository.findAll();
//
//        for (Player player : players) {System.out.println(player);}
//    }
//
//    public void newCard(){
//        Card card = new Card("Parcerias e meios de implementação", "Parcerias e meios de implementação", "https://gtagenda2030.org.br/wp-content/uploads/2019/10/ods17.jpg?w=640");
//        cardRepository.save(card);
//    }
//
//    public void loadCards() {
//        List<Card> allCards = cardRepository.findAll();
//
//        cards.clear();
//
//        for (Card card : allCards) {
//            cards.add(card);
//            cards.add(new Card(card.getName(), card.getDescription(), card.getImgUrl()));
//        }
//
//        shuffle();
//
//        for (Card c : cards) {
//            System.out.println(c);
//        }
//
//    }
//
//    public void shuffle() {
//        Collections.shuffle(cards);
//    }
//
//}
