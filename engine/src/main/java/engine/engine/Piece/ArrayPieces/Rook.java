package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;
import java.util.Map;

public class Rook extends Piece {

    public Rook(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
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
        int x_ = this.getX();
        int y_ = this.getY();
        if (x != x_ && y != y_) {
            return false;
        }
        if (x == x_ && y == y_) {
            return false;
        }

        if (type == 1) {
            int kingx = -1, kingy = -1;
            Piece p1 = occupied.get(x_ * 8 + y_);
            if (occupied.containsKey(x_ * 8 + y_)) {
                occupied.remove(x_ * 8 + y_);
            }
            ;
            for (Map.Entry<Integer, Piece> i : occupied.entrySet()) {
                int cord = i.getKey();
                Piece p = i.getValue();
                int currx = cord / 8, curry = cord % 8;
                if (currx == x && curry == y)
                    continue;
                if (currx == x_ && curry == y_)
                    continue;
                boolean conditionSat = (whoseTurn == 1 ? (p.getFenRep().equals("K")) : (p.getFenRep().equals("k")));
                if (conditionSat) {
                    kingx = p.getX();
                    kingy = p.getY();
                    break;
                }
            }

            for (Map.Entry<Integer, Piece> i : occupied.entrySet()) {
                int cord = i.getKey();
                Piece p = i.getValue();
                int currx = cord / 8, curry = cord % 8;
                if (currx == x && curry == y)
                    continue;
                if (currx == x_ && curry == y_)
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

    @Override
    public String getFenRep() {
        return (getColor() == 1 ? "R" : "r");
    }

    @Override
    public Piece createCopy(HashMap<Integer, Piece> occupied) {
        Piece p = new Rook(this.x, this.y, this.hasMoved, this.getColor(), occupied);
        return p;
    }
}
