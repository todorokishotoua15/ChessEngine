package engine.engine.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import engine.engine.Board.ArrayBoard.ArrayBoard;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArrayBoardTest {
    @Autowired
    private ArrayBoard board;

    @BeforeAll
    public void setup() {
        board.reset();

    }

    @Test
    public void fenTest() {
        log.info(board.getOccupied().containsKey(0) ? "Yes" : "NO");
        String fen1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
        assertEquals(board.getState(), fen1);
        assertTrue(board.move(1, 3, 3, 3));
        String fen2 = "rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq d3 0 0";
        assertEquals(board.getState(), fen2);
        assertTrue(board.move(6, 4, 4, 4));
        String fen3 = "rnbqkbnr/pppp1ppp/8/4p3/3P4/8/PPP1PPPP/RNBQKBNR w KQkq e6 0 0";
        assertEquals(board.getState(), fen3);
        assertTrue(board.move(0, 2, 3, 5));
        String fen4 = "rnbqkbnr/pppp1ppp/8/4p3/3P1B2/8/PPP1PPPP/RN1QKBNR b KQkq - 0 0";
        assertEquals(board.getState(), fen4);
        assertTrue(board.move(4, 4, 3, 5));
        String fen5 = "rnbqkbnr/pppp1ppp/8/8/3P1p2/8/PPP1PPPP/RN1QKBNR w KQkq - 0 0";
        assertEquals(board.getState(), fen5);
        assertTrue(board.move(0, 1, 2, 2));
        String fen6 = "rnbqkbnr/pppp1ppp/8/8/3P1p2/2N5/PPP1PPPP/R2QKBNR b KQkq - 0 0";
        assertEquals(board.getState(), fen6);
        assertTrue(board.move(7, 5, 3, 1));
        String fen7 = "rnbqk1nr/pppp1ppp/8/8/1b1P1p2/2N5/PPP1PPPP/R2QKBNR w KQkq - 0 0";
        assertEquals(board.getState(), fen7);
        assertTrue(board.move(1, 0, 2, 0));
        String fen8 = "rnbqk1nr/pppp1ppp/8/8/1b1P1p2/P1N5/1PP1PPPP/R2QKBNR b KQkq - 0 0";
        assertEquals(board.getState(), fen8);
        assertTrue(board.move(3, 1, 2, 2));
        String fen9 = "rnbqk1nr/pppp1ppp/8/8/3P1p2/P1b5/1PP1PPPP/R2QKBNR w KQkq - 0 0";
        assertEquals(board.getState(), fen9);
    }
}
