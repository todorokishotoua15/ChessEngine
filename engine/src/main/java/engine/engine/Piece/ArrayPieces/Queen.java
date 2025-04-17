package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;

public class Queen extends Piece {

    public Queen(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
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
        int x_ = this.getX(), y_ = this.getY();
        if (x == x_ && y == y_)
            return false;
        int disx = Math.abs(x - x_), disy = Math.abs(y - y_);
        if (Math.min(disx, disy) == 0) {
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
        } else if (disx != disy)
            return false;
        int stepx = (x > x_ ? 1 : -1);
        int stepy = (y > y_ ? 1 : -1);
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
