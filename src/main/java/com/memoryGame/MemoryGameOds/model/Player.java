package com.memoryGame.MemoryGameOds.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.memoryGame.MemoryGameOds.DTO.PlayerResquestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "player")
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nacionality;
    private Integer movements ;
    @JsonManagedReference
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private Score score;

    public Player(PlayerResquestDTO data) {
        this.name = data.name();
        this.nacionality = data.nacionality();
        this.movements = 24;
        Score s = new Score(0, 0);
        s.setPlayer(this);
        this.score = s;
    }

}
