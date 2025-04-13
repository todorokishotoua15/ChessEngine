package engine.engine.Piece;

public class Bishop extends Piece{

    @Override
    public boolean checkValidMove(int x, int y) {
        if (occupied.containsKey(x*8+y)) {
            Piece p = occupied.get(x*8+y);
            if (p.getColor() == this.getColor()) {
                return false;
            }
        }
        int x_ = this.getX(), y_ = this.getY();
        int disx = Math.abs(x-x_);
        int disy = Math.abs(y-y_);
        if (disx != disy) return false;
        if (x == x_ && y == y_) return false;
        int stepx = (x > x_ ? 1 : -1);
        int stepy = (y > y_ ? 1 : -1);
        while (true) {
            y_ += stepy;
            x_ += stepx;
            if (occupied.containsKey(x_*8 + y_)) {
                return false;
            }
            if (y_ == y && x == x_) break;
        }
        return true;
    }
    
}
