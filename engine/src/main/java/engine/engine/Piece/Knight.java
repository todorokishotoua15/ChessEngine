package engine.engine.Piece;

public class Knight extends Piece {

    @Override
    public boolean checkValidMove(int x, int y) {
        if (occupied.containsKey(x*8+y)) {
            Piece p = occupied.get(x*8+y);
            if (p.getColor() == this.getColor()) {
                return false;
            }
        }
        int x_ = this.getX(), y_ = this.getY();
        if (x == x_ && y == y_)
            return false;
        int disx = Math.abs(x - x_), disy = Math.abs(y - y_);
        if (Math.min(disx, disy) != 1 || Math.max(disx, disy) != 2) {
            return false;
        }
        if (occupied.containsKey(x * 8 + y)) {
            return false;
        }
        return true;
    }

}
