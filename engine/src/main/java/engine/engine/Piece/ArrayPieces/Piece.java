package engine.engine.Piece.ArrayPieces;

import java.util.HashMap;

import lombok.Data;

@Data
public abstract class Piece {
    private int x, y;
    private boolean active;
    private int color;
    protected boolean hasMoved;

    public Piece(int x_, int y_, boolean active_, int color_, HashMap<Integer, Piece> occupied) {
        x = x_;
        y = y_;
        active = active_;
        color = color_;
        hasMoved = false;
        occupied.put(x * 8 + y, this);
    }

    public boolean move(int x, int y, HashMap<Integer, Piece> occupied) {

        if (occupied.containsKey(this.x * 8 + this.y))
            occupied.remove(Integer.valueOf(this.x * 8 + this.y));
        setHasMoved(true);
        setX(x);
        setY(y);
        occupied.put(Integer.valueOf(x * 8 + y), this);
        return true;
    };

    public abstract boolean checkValidMove(int x, int y, HashMap<Integer, Piece> occupied);

    public abstract String getFenRep();
}
