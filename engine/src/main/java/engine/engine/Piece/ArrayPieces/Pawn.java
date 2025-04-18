package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;

public class Pawn extends Piece {

    public Pawn(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
        super(x_, y_, active_, color_, occupied);
    }

    @Override
    public boolean checkValidMove(int x, int y, HashMap<Integer, Piece> occupied) {
        int x_ = this.getX(), y_ = this.getY();
        if (y != y_) {
            if (this.getColor() == 1) {
                if (x != x_ + 1) {
                    return false;
                } else {
                    if (!occupied.containsKey(x * 8 + y))
                        return false;
                    if (occupied.get(x * 8 + y).getColor() == this.getColor())
                        return false;
                    return true;
                }
            } else {
                if (x != x_ - 1) {
                    return false;
                } else {
                    if (!occupied.containsKey(x * 8 + y))
                        return false;
                    if (occupied.get(x * 8 + y).getColor() == this.getColor())
                        return false;
                    return true;
                }
            }
        } else {
            if (occupied.containsKey(x * 8 + y))
                return false;
            int step = (x > x_ ? 1 : -1);
            if (step == 1) {
                if (this.getColor() != 1) {
                    return false;
                }
            } else {
                if (this.getColor() != 0) {
                    return false;
                }
            }
            int dis = Math.abs(x - x_);
            if (dis == 2) {
                if (hasMoved) {
                    return false;
                } else {
                    if (occupied.containsKey((x_ + step) * 8 + y)) {
                        return false;
                    }
                }
            }
            if (dis > 2)
                return false;
            return true;
        }
    }

    @Override
    public String getFenRep() {
        return (getColor() == 1 ? "P" : "p");
    }

}
