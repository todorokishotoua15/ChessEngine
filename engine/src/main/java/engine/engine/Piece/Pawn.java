package engine.engine.Piece;

public class Pawn extends Piece {

    @Override
    public boolean checkValidMove(int x, int y) {
        int x_ = this.getX(), y_ = this.getY();
        if (x != x_) {
            if (this.getColor() == 1) {
                if (y != y_ + 1) {
                    return false;
                } else {
                    if (!occupied.containsKey(x * 8 + y))
                        return false;
                    if (occupied.get(x * 8 + y).getColor() == this.getColor())
                        return false;
                    return true;
                }
            } else {
                if (y != y_ - 1) {
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
            int step = (y > y_ ? 1 : -1);
            if (step == 1) {
                if (this.getColor() != 1) {
                    return false;
                }
            } else {
                if (this.getColor() != 0) {
                    return false;
                }
            }
            int dis = Math.abs(y - y_);
            if (dis == 2) {
                if (hasMoved) {
                    return false;
                }
            }
            if (dis > 2)
                return false;
            return true;
        }
    }

}
