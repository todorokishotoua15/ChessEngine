package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;

public class King extends Piece {

    public King(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
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
        if (disx + disy != 1)
            return false;
        if (occupied.containsKey(x * 8 + y))
            return false;
        return true;
    }

    @Override
    public String getFenRep() {
        return (getColor() == 1 ? "K" : "k");
    }

}
