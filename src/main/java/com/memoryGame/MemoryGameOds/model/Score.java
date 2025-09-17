package com.memoryGame.MemoryGameOds.model;

import jakarta.persistence.*;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "player_id", unique = true, nullable = false)
    private Player player;
    @Column(nullable = false)
    private Integer score;
    @Column(nullable = false)
    private Integer timeSeconds;

    public Score() {}

    public Score(Integer score, Integer timeSeconds) {
        this.score = score;
        this.timeSeconds = timeSeconds;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(Integer timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score=" + score +
                ", timeSeconds=" + timeSeconds +
                '}';
    }
}
