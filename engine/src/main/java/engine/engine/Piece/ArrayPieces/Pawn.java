package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pawn extends Piece {

    public Pawn(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
        super(x_, y_, active_, color_, occupied);
    }

    @Override
    public boolean checkValidMove(int x, int y, HashMap<Integer, Piece> occupied, int whoseTurn, int type) {
        int x_ = this.getX(), y_ = this.getY();

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

    @Override
    public Piece createCopy(HashMap<Integer, Piece> occupied) {
        Piece p = new Pawn(this.x, this.y, this.hasMoved, this.getColor(), occupied);
        return p;
    }

}
