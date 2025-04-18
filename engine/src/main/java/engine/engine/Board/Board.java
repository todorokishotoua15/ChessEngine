package engine.engine.Board;

import org.springframework.stereotype.Component;

@Component
public interface Board {
    public String getState();

    public String blackCastling();

    public String whiteCastling();

    public String enPassant();

    public boolean move(int x1, int y1, int x2, int y2);
}
