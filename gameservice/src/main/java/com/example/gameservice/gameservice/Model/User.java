package com.example.gameservice.gameservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String email;
    private String name;
    private String password;
    private int rating;
    private int computerGames;
    private int vsGames;
    private int computerGamesWon;
    private int vsGamesWon;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        rating = 0;
        computerGames = 0;
        vsGames = 0;
        computerGamesWon = 0;
        vsGamesWon = 0;
    }

}