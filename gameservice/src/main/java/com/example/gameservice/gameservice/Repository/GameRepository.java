package com.example.gameservice.gameservice.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.gameservice.gameservice.Model.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {
    @Query("select g from Game g where g.player1 = ?1 and g.id = ?2")
    Game findGameByUserId(String email, int game_id);

    @Query("select g from Game g where g.player1 = ?1 ")
    Collection<Game> findAllGames(String user_email);

    @Query("select g from Game g where g.player1 = ?1 and g.finished=0")
    Collection<Game> findUnfinishedGames(String user_email);
}
