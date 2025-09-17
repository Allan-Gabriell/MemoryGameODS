package com.memoryGame.MemoryGameOds.model;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nacionality;
    private static Integer movements = 24;
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private Score score;

    public Player(){}

    public Player(String name, String nacionality) {
        this.name = name;
        this.nacionality = nacionality;
        this.score = new Score(0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                ", score=" + score;
    }
}
