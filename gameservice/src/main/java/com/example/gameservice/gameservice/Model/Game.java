package com.example.gameservice.gameservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "game", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Game {
    @Id
    private int id;
    private String fen_string;
    private String player1;
    private String player2;
    private int engine_id;
    private int finished;
}
