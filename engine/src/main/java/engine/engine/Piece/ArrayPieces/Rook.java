package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;

public class Rook extends Piece {

    public Rook(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
        super(x_, y_, active_, color_, occupied);
    }

    @Override
    public boolean checkValidMove(int x, int y, HashMap<Integer, Piece> occupied) {
        if (occupied.containsKey(x * 8 + y)) {
            Piece p = occupied.get(x * 8 + y);
            if (p.getColor() == this.getColor()) {
                return false;
            }
        }
        int x_ = this.getX();
        int y_ = this.getY();
        if (x != x_ && y != y_) {
            return false;
        }
        if (x == x_ && y == y_) {
            return false;
        }
        int stepy = (y > y_ ? 1 : y == y_ ? 0 : -1);
        int stepx = (x > x_ ? 1 : x == x_ ? 0 : -1);
        while (true) {
            y_ += stepy;
            x_ += stepx;
            if (y_ == y && x == x_)
                break;
            if (occupied.containsKey(x_ * 8 + y_)) {
                return false;
            }
        }
        return true;
    }
}
