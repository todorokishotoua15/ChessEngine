package engine.engine.Piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import engine.engine.Piece.ArrayPieces.Bishop;
import engine.engine.Piece.ArrayPieces.King;
import engine.engine.Piece.ArrayPieces.Knight;
import engine.engine.Piece.ArrayPieces.Pawn;
import engine.engine.Piece.ArrayPieces.Piece;
import engine.engine.Piece.ArrayPieces.Queen;
import engine.engine.Piece.ArrayPieces.Rook;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PieceTest {
    private static ArrayList<Piece> board;
    private static HashMap<Integer,Piece> occupied;
    @BeforeAll
    public static void setup() {

        board = new ArrayList<Piece>();
        occupied = new HashMap<Integer, Piece>();

        board.add(new Rook(0, 0, true, 1, occupied));
        board.add(new Knight(0, 1, true, 1, occupied));
        board.add(new Bishop(0, 2, true, 1, occupied));
        board.add(new Queen(0, 3, true, 1, occupied));
        board.add(new King(0, 4, true, 1, occupied));
        board.add(new Bishop(0, 5, true, 1, occupied));
        board.add(new Knight(0, 6, true, 1, occupied));
        board.add(new Rook(0, 7, true, 1, occupied));

        board.add(new Rook(7, 0, true, 0, occupied));
        board.add(new Knight(7, 1, true, 0, occupied));
        board.add(new Bishop(7, 2, true, 0, occupied));
        board.add(new Queen(7, 3, true, 0, occupied));
        board.add(new King(7, 4, true, 0, occupied));
        board.add(new Bishop(7, 5, true, 0, occupied));
        board.add(new Knight(7, 6, true, 0, occupied));
        board.add(new Rook(7, 7, true, 0, occupied));

        for (int i = 0; i < 8; i++) {
            board.add(new Pawn(1, i, true, 1, occupied));
            board.add(new Pawn(6, i, true, 0, occupied));
        }
    }

    @Test
    public void performMoves() {
        assertFalse(board.get(16).checkValidMove(0, 3, occupied));
        assertTrue(board.get(16).checkValidMove(3, 0, occupied));
        assertTrue(board.get(16).move(3, 0, occupied));
        assertTrue(board.get(23).checkValidMove(5, 3, occupied));
        assertTrue(board.get(23).move(5, 3, occupied));
        assertFalse(board.get(2).checkValidMove(1, 1, occupied));
        assertTrue(board.get(10).checkValidMove(6, 3, occupied));
        assertTrue(board.get(10).move(6, 3, occupied));
        assertTrue(board.get(10).checkValidMove(3, 0, occupied));
        assertTrue(board.get(10).move(3, 0, occupied));
    }
}
