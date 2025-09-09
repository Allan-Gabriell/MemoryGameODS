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
    static Integer movements = 24;

    public Player(){}

    public Player(String name, String nacionality) {
        this.name = name;
        this.nacionality = nacionality;
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

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                "Movements: " + movements;
    }
}
