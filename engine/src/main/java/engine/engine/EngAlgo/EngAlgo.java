package engine.engine.EngAlgo;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import engine.engine.Piece.ArrayPieces.Piece;

public interface EngAlgo {
    ArrayList<Integer> nextMove(HashMap<Integer, Piece> occupied, int color);
}
