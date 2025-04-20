package engine.engine.Board.ArrayBoard;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import engine.engine.Board.Board;
import engine.engine.Piece.ArrayPieces.Bishop;
import engine.engine.Piece.ArrayPieces.King;
import engine.engine.Piece.ArrayPieces.Knight;
import engine.engine.Piece.ArrayPieces.Pawn;
import engine.engine.Piece.ArrayPieces.Piece;
import engine.engine.Piece.ArrayPieces.Queen;
import engine.engine.Piece.ArrayPieces.Rook;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component("ArrayBoard")
public class ArrayBoard implements Board {
    private ArrayList<Piece> pieces;
    private HashMap<Integer, Piece> occupied;
    private int whoseTurn;
    private int enPassantx;
    private int enPassanty;

    public void init() {
        pieces = new ArrayList<Piece>();
        occupied = new HashMap<Integer, Piece>();
        whoseTurn = 1;
        enPassantx = -1;
        enPassanty = -1;

        pieces.add(new Rook(0, 0, true, 1, occupied));
        pieces.add(new Knight(0, 1, true, 1, occupied));
        pieces.add(new Bishop(0, 2, true, 1, occupied));
        pieces.add(new Queen(0, 3, true, 1, occupied));
        pieces.add(new King(0, 4, true, 1, occupied));
        pieces.add(new Bishop(0, 5, true, 1, occupied));
        pieces.add(new Knight(0, 6, true, 1, occupied));
        pieces.add(new Rook(0, 7, true, 1, occupied));

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

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public void reset() {
        init();
    }

    public ArrayBoard() {
        init();
    }

    public String getState() {
        String fen = "";
        // phase 1
        for (int row = 7; row >= 0; row--) {
            int space = 0;
            for (int col = 0; col < 8; col++) {
                if (!occupied.containsKey(row * 8 + col)) {
                    space++;
                } else {
                    if (space != 0) {
                        fen += String.valueOf(space);
                        space = 0;
                        fen += occupied.get(row * 8 + col).getFenRep();
                    } else {
                        fen += occupied.get(row * 8 + col).getFenRep();
                    }
                }
            }
            if (space != 0) {
                fen += String.valueOf(space);
                space = 0;
            }
            if (row != 0)
                fen += "/";
            else
                fen += " ";
        }

        // phase 2 (active color)
        if (whoseTurn == 1) {
            fen += "w ";
        } else {
            fen += "b ";
        }

        // phase 3 Castling Rights
        String temp1 = whiteCastling();
        String temp2 = blackCastling();

        if (temp1.equals("-")&&temp2.equals("-")) {
            fen += '-';
        }
        else {
            if (!temp1.equals("-")) {
                fen += temp1;
            }
            if (!temp2.equals("-")) {
                fen += temp2;
            }
        }

        fen += " ";

        // phase 4 Possible En Passant Targets
        fen += enPassant();

        // phase 5 Halfmove Clock
        fen += " ";
        fen += "0";

        // phase 6 Fullmove Number
        fen += " ";
        fen += "0";

        return fen;
    }

    @Override
    public String whiteCastling() {
        boolean kingside = false;
        boolean queenside = false;
        if (occupied.containsKey(0 * 8 + 4) && occupied.containsKey(0 * 8 + 0)) {
            if (occupied.get(0 * 8 + 4).getFenRep().equals("K") && occupied.get(0 * 8 + 0).getFenRep().equals("R")) {
                queenside = true;
            }
        }
        if (occupied.containsKey(0 * 8 + 4) && occupied.containsKey(0 * 8 + 7)) {
            if (occupied.get(0 * 8 + 4).getFenRep().equals("K") && occupied.get(0 * 8 + 7).getFenRep().equals("R")) {
                kingside = true;
            }
        }
        String fen = "";
        if (!kingside && !queenside) {
            fen = "-";
        } else {
            if (kingside)
                fen += "K";
            if (queenside)
                fen += "Q";
        }
        return fen;
    }

    @Override
    public String blackCastling() {
        boolean kingside = false;
        boolean queenside = false;
        if (occupied.containsKey(7 * 8 + 4) && occupied.containsKey(7 * 8 + 0)) {
            if (occupied.get(7 * 8 + 4).getFenRep().equals("k") && occupied.get(7 * 8 + 0).getFenRep().equals("r")) {
                queenside = true;
            }
        }
        if (occupied.containsKey(7 * 8 + 4) && occupied.containsKey(7 * 8 + 7)) {
            if (occupied.get(7 * 8 + 4).getFenRep().equals("k") && occupied.get(7 * 8 + 7).getFenRep().equals("r")) {
                kingside = true;
            }
        }
        String fen = "";
        if (!kingside && !queenside) {
            fen = "-";
        } else {
            if (kingside)
                fen += "k";
            if (queenside)
                fen += "q";
        }
        return fen;
    }

    @Override
    public String enPassant() {
        if (enPassantx == -1 || enPassanty == -1) {
            return "-";
        } else {
            char a = 'a';
            a += enPassanty;
            char b = '1';
            b += enPassantx;
            String fen = "";
            fen += a;
            fen += b;
            return fen;
        }
    }

    public boolean move(int x1, int y1, int x2, int y2) {
        if (!occupied.containsKey(x1 * 8 + y1))
            return false;
        Piece p = occupied.get(x1 * 8 + y1);
        if (!p.checkValidMove(x2, y2, occupied, whoseTurn, 1))
            return false;
        int color = p.getColor();
        if (color != whoseTurn)
            return false;
        enPassantx = -1;
        enPassanty = -1;
        whoseTurn = (whoseTurn == 1 ? 0 : 1);
        occupied.remove(x1 * 8 + y1);
        if (occupied.containsKey(x2 * 8 + y2)) {
            occupied.get(x2 * 8 + y2).setActive(false);
            pieces.remove(occupied.get(x2 * 8 + y2));
            occupied.remove(x2 * 8 + y2);
        }
        p.move(x2, y2, occupied);
        if (p.getFenRep() == "P" || p.getFenRep() == "p") {
            int dis = Math.abs(x1 - x2);
            if (dis == 2) {
                int step = (x2 > x1 ? 1 : -1);
                enPassantx = x2 - step;
                enPassanty = y2;
            }
        }
        return true;
    }

}
