package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bishop extends Piece {

    public Bishop(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
        super(x_, y_, active_, color_, occupied);
    }

    @Override
    public boolean checkValidMove(int x, int y, HashMap<Integer, Piece> occupied) {
        if (occupied.containsKey(x * 8 + y)) {
            log.info("We are here");
            Piece p = occupied.get(x * 8 + y);
            if (p.getColor() == this.getColor()) {
                return false;
            }
        }
        int x_ = this.getX(), y_ = this.getY();
        int disx = Math.abs(x - x_);
        int disy = Math.abs(y - y_);
        if (disx != disy)
            return false;
        if (x == x_ && y == y_)
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

    @Override
    public String getFenRep() {
        if (this.getColor() == 1) {
            return "B";
        } else {
            return "b";
        }
    }

}
