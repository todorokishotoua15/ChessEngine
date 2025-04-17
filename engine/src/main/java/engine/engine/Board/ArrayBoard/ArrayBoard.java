package engine.engine.Board.ArrayBoard;

import java.util.ArrayList;
import java.util.HashMap;

import engine.engine.Board.Board;
import engine.engine.Piece.ArrayPieces.Bishop;
import engine.engine.Piece.ArrayPieces.King;
import engine.engine.Piece.ArrayPieces.Knight;
import engine.engine.Piece.ArrayPieces.Pawn;
import engine.engine.Piece.ArrayPieces.Piece;
import engine.engine.Piece.ArrayPieces.Queen;
import engine.engine.Piece.ArrayPieces.Rook;

public class ArrayBoard implements Board {
    ArrayList<Piece> pieces;
    HashMap<Integer, Piece> occupied;
    int whoseTurn;
    ArrayBoard() {
        pieces = new ArrayList<Piece>();
        occupied = new HashMap<Integer, Piece>();
        whoseTurn = 1;

        pieces.add(new Rook(0, 0, true, 1,occupied));
        pieces.add(new Knight(0, 1, true, 1,occupied));
        pieces.add(new Bishop(0, 2, true, 1,occupied));
        pieces.add(new Queen(0, 3, true, 1,occupied));
        pieces.add(new King(0, 4, true, 1,occupied));
        pieces.add(new Bishop(0, 5, true, 1,occupied));
        pieces.add(new Knight(0, 6, true, 1,occupied));
        pieces.add(new Rook(0, 7, true, 1,occupied));

        pieces.add(new Rook(7, 0, true, 0, occupied));
        pieces.add(new Knight(7, 1, true, 0, occupied));
        pieces.add(new Bishop(7, 2, true, 0, occupied));
        pieces.add(new Queen(7, 3, true, 0, occupied));
        pieces.add(new King(7, 4, true, 0, occupied));
        pieces.add(new Bishop(7, 5, true, 0, occupied));
        pieces.add(new Knight(7, 6, true, 0, occupied));
        pieces.add(new Rook(7, 7, true, 0, occupied));

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(1, i, true, 1, occupied));
            pieces.add(new Pawn(6, i, true, 0, occupied));
        }
    }
}
