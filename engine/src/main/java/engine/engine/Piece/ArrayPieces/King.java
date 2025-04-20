package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;
import java.util.Map;

public class King extends Piece {

    public King(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
        super(x_, y_, active_, color_, occupied);
    }

    @Override
    public boolean checkValidMove(int x, int y, HashMap<Integer, Piece> occupied, int whoseTurn, int type) {
        if (occupied.containsKey(x * 8 + y)) {
            Piece p = occupied.get(x * 8 + y);
            if (p.getColor() == this.getColor()) {
                return false;
            }
        }
        int x_ = this.getX(), y_ = this.getY();
        if (x == x_ && y == y_)
            return false;

        if (type == 1) {
            int kingx = x, kingy = y;
            Piece p1 = occupied.get(x_ * 8 + y_);
            if (occupied.containsKey(x_ * 8 + y_)) {
                occupied.remove(x_ * 8 + y_);
            }
            ;
            for (Map.Entry<Integer, Piece> i : occupied.entrySet()) {
                int cord = i.getKey();
                Piece p = i.getValue();
                int currx = cord / 8, curry = cord % 8;
                if (currx == x_ && curry == y_)
                    continue;
                if (currx == x && curry == y)
                    continue;
                if (p.getColor() != whoseTurn) {
                    if (p.checkValidMove(kingx, kingy, occupied, whoseTurn, 0)) {
                        occupied.put(x_ * 8 + y_, p1);
                        return false;
                    }
                }
            }
            occupied.put(x_ * 8 + y_, p1);
        }

        int disx = Math.abs(x - x_), disy = Math.abs(y - y_);
        if (Math.max(disx, disy) != 1)
            return false;
        if (occupied.containsKey(x * 8 + y))
            return false;
        return true;
    }

    @Override
    public String getFenRep() {
        return (getColor() == 1 ? "K" : "k");
    }

    @Override
    public Piece createCopy(HashMap<Integer, Piece> occupied) {
        Piece p = new King(this.x, this.y, this.hasMoved, this.getColor(), occupied);
        return p;
    }

}
