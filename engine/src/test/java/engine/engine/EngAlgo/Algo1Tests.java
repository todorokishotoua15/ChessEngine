package engine.engine.EngAlgo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import engine.engine.Board.Board;
import engine.engine.Board.ArrayBoard.ArrayBoard;
import engine.engine.EngAlgo.Algo1.Algo1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Algo1Tests {

    private EngAlgo algo;

    @Autowired
    private ArrayBoard board;

    @BeforeAll
    public void setup() {
        algo = new Algo1();
        board.reset();
    }

    @Test
    public void nextMoveTest() {

        int iterations = 500;
        for (int i = 0; i < iterations; i++) {
            ArrayList<Integer> nextMove = algo.nextMove(board.getOccupied(), board.getWhoseTurn());
            log.info("{}", nextMove);
            log.info("{}", board.getOccupied().get(nextMove.get(0) * 8 + nextMove.get(1)));

            assertTrue(board.move(nextMove.get(0), nextMove.get(1), nextMove.get(2), nextMove.get(3)));
            log.info("Iteration : {}, State : {}", i + 1, board.getState());
        }

    }

}
