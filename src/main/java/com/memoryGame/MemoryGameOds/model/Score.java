package com.memoryGame.MemoryGameOds.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "score")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "player_id", unique = true, nullable = false)
    private Player player;

    @Column(nullable = false)
    private Integer score = 0;

    @Column(nullable = false)
    private Integer timeSeconds = 0;

    // Construtor personalizado
    public Score(Integer score, Integer timeSeconds, Player player) {
        this.score = score;
        this.timeSeconds = timeSeconds;
        this.player = player;
    }

    public Score(Integer score, Integer timeSeconds) {
        this.score = score;
        this.timeSeconds = timeSeconds;
    }
}
