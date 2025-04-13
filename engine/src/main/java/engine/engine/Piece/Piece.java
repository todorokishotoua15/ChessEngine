package engine.engine.Piece;

import java.util.HashMap;

import lombok.Data;

@Data
public abstract class Piece {
    private int x, y;
    private boolean active;
    private int color;
    protected boolean hasMoved;
    protected static HashMap<Integer, Piece> occupied = new HashMap<Integer, Piece>();

    public boolean move(int x, int y) {
        if (!checkValidMove(x, y)) {
            return false;
        }
        occupied.remove(Integer.valueOf(x * 8 + y));
        setHasMoved(true);
        setX(x);
        setY(y);
        occupied.put(Integer.valueOf(x * 8 + y), this);
        return true;
    };

    public abstract boolean checkValidMove(int x, int y);
}
