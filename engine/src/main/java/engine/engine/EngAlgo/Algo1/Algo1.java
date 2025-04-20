package engine.engine.EngAlgo.Algo1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import engine.engine.EngAlgo.EngAlgo;
import engine.engine.Piece.ArrayPieces.Piece;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Algo1 implements EngAlgo {

    private HashMap<String, Integer> pieceValueOpening, pieceValueEnding;
    private HashMap<String, ArrayList<Integer>> positionValueOpening, positionValueEnding;

    public Algo1() {

        pieceValueOpening = new HashMap<String, Integer>();
        pieceValueEnding = new HashMap<String, Integer>();

        positionValueOpening = new HashMap<String, ArrayList<Integer>>();
        positionValueEnding = new HashMap<String, ArrayList<Integer>>();

        pieceValueOpening.put("P", 89);
        pieceValueOpening.put("N", 308);
        pieceValueOpening.put("B", 319);
        pieceValueOpening.put("R", 488);
        pieceValueOpening.put("Q", 888);
        pieceValueOpening.put("K", 20001);

        pieceValueOpening.put("p", -92);
        pieceValueOpening.put("n", -307);
        pieceValueOpening.put("b", -323);
        pieceValueOpening.put("r", -492);
        pieceValueOpening.put("q", -888);
        pieceValueOpening.put("k", -20002);

        pieceValueEnding.put("P", 96);
        pieceValueEnding.put("N", 319);
        pieceValueEnding.put("B", 331);
        pieceValueEnding.put("R", 497);
        pieceValueEnding.put("Q", 853);
        pieceValueEnding.put("K", 19998);

        pieceValueEnding.put("p", -102);
        pieceValueEnding.put("n", -318);
        pieceValueEnding.put("b", -334);
        pieceValueEnding.put("r", -501);
        pieceValueEnding.put("q", -845);
        pieceValueEnding.put("k", -20000);

        // pawn
        positionValueOpening.put("P", new ArrayList<Integer>(Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0,
                -4, 68, 61, 47, 47, 49, 45, -1,
                6, 16, 25, 33, 24, 24, 14, -6,
                0, -1, 9, 28, 20, 8, -1, 11,
                6, 4, 6, 14, 14, -5, 6, -6,
                -1, -8, -4, 4, 2, -12, -1, 5,
                5, 16, 16, -14, -14, 13, 15, 8,
                0, 0, 0, 0, 0, 0, 0, 0)));

        // knight
        positionValueOpening.put("N", new ArrayList<Integer>(Arrays.asList(
                -55, -40, -30, -28, -26, -30, -40, -50,
                -37, -15, 0, -6, 4, 3, -17, -40,
                -25, 5, 16, 12, 11, 6, 6, -29,
                -24, 5, 21, 14, 18, 9, 11, -26,
                -36, -5, 9, 23, 24, 21, 2, -24,
                -32, -1, 4, 19, 20, 4, 11, -25,
                -38, -22, 4, -1, 8, -5, -18, -34,
                -50, -46, -32, -24, -36, -25, -34, -50)));

        // bishop
        positionValueOpening.put("B", new ArrayList<Integer>(Arrays.asList(
                -16, -15, -12, -5, -10, -12, -10, -20,
                -13, 5, 6, 1, -6, -5, 3, -6,
                -16, 6, -1, 16, 7, -1, -6, -5,
                -14, -1, 11, 14, 4, 10, 11, -13,
                -4, 5, 12, 16, 4, 6, 2, -16,
                -15, 4, 14, 8, 16, 4, 16, -15,
                -5, 6, 6, 6, 3, 6, 9, -7,
                -14, -4, -15, -4, -9, -4, -12, -14)));

        // rook
        positionValueOpening.put("R", new ArrayList<Integer>(Arrays.asList(
                5, -2, 6, 2, -2, -6, 4, -2,
                8, 13, 11, 15, 11, 15, 16, 4,
                -6, 3, 3, 6, 1, -2, 3, -5,
                -10, 5, -4, -4, -1, -6, 3, -2,
                -4, 3, 5, -2, 4, 1, -5, 1,
                0, 1, 1, -3, 5, 6, 1, -9,
                -10, -1, -4, 0, 5, -6, -6, -9,
                -1, -2, -6, 9, 9, 5, 4, -5)));

        // queen
        positionValueOpening.put("Q", new ArrayList<Integer>(Arrays.asList(
                -25, -9, -11, -3, -7, -13, -10, -17,
                -4, -6, 4, -5, -1, 6, 4, -5,
                -8, -5, 2, 0, 7, 6, -4, -5,
                0, -4, 7, -1, 7, 11, 0, 1,
                -6, 4, 7, 1, -1, 2, -6, -2,
                -15, 11, 11, 11, 4, 11, 6, -15,
                -5, -6, 1, -6, 3, -3, 3, -10,
                -15, -4, -13, -8, -3, -16, -8, -24)));

        // king
        positionValueOpening.put("K", new ArrayList<Integer>(Arrays.asList(
                -30, -40, -40, -50, -50, -40, -40, -30,
                -30, -37, -43, -49, -50, -39, -40, -30,
                -32, -41, -40, -46, -49, -40, -46, -30,
                -32, -38, -39, -52, -54, -39, -39, -30,
                -20, -33, -29, -42, -44, -29, -30, -19,
                -10, -18, -17, -20, -22, -21, -20, -13,
                14, 18, -1, -1, 4, -1, 15, 14,
                21, 35, 11, 6, 1, 14, 32, 22)));

        // pawn
        positionValueEnding.put("P", new ArrayList<Integer>(Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0,
                -4, 174, 120, 94, 85, 98, 68, 4,
                6, 48, 44, 45, 31, 38, 37, -6,
                -6, -4, -1, -6, 2, -1, -2, -2,
                2, 2, 5, -3, 0, -5, 4, -3,
                -2, 0, 1, 5, 0, -1, 0, 1,
                -2, 5, 6, -6, 0, 3, 4, -4,
                0, 0, 0, 0, 0, 0, 0, 0)));

        // knight
        positionValueEnding.put("N", new ArrayList<Integer>(Arrays.asList(
                -50, -40, -30, -24, -24, -35, -40, -50,
                -38, -17, 6, -5, 5, -4, -15, -40,
                -24, 3, 15, 9, 15, 10, -6, -26,
                -29, 5, 21, 17, 18, 9, 10, -28,
                -36, -5, 18, 16, 14, 20, 5, -26,
                -32, 7, 5, 20, 11, 15, 9, -27,
                -43, -20, 5, -1, 5, 1, -22, -40,
                -50, -40, -32, -27, -30, -25, -35, -50)));

        // bishop
        positionValueEnding.put("B", new ArrayList<Integer>(Arrays.asList(
                -14, -13, -4, -7, -14, -9, -16, -20,
                -11, 6, 3, -6, 4, -3, 5, -4,
                -11, -3, 5, 15, 4, -1, -5, -10,
                -7, -1, 11, 16, 5, 11, 7, -13,
                -4, 4, 10, 16, 6, 12, 4, -16,
                -4, 4, 11, 12, 10, 7, 7, -12,
                -11, 7, 6, 6, -3, 2, 1, -7,
                -15, -4, -11, -4, -10, -10, -6, -17)));

        // rook
        positionValueEnding.put("R", new ArrayList<Integer>(Arrays.asList(
                5, -6, 1, -4, -4, -6, 6, -3,
                -6, 4, 2, 5, -1, 3, 4, -15,
                -15, 3, 3, 0, -1, -6, 5, -9,
                -16, 6, 0, -6, -3, -3, -4, -4,
                -15, 6, 2, -6, 6, 0, -6, -10,
                -6, -1, 3, -2, 6, 5, 0, -15,
                -8, -4, 1, -4, 3, -5, -6, -5,
                1, 0, -2, 1, 1, 4, 2, 0)));

        // queen
        positionValueEnding.put("Q", new ArrayList<Integer>(Arrays.asList(
                -21, -7, -6, 1, -8, -15, -10, -16,
                -4, -5, 3, -4, 2, 6, 3, -10,
                -13, -2, 7, 2, 6, 10, -4, -6,
                -1, -4, 3, 1, 8, 8, -2, -2,
                0, 6, 8, 1, -1, 1, 0, -3,
                -11, 10, 6, 3, 7, 9, 4, -10,
                -12, -6, 5, 0, 0, -5, 4, -10,
                -20, -6, -7, -7, -4, -12, -9, -20)));

        // king
        positionValueEnding.put("K", new ArrayList<Integer>(Arrays.asList(
                -50, -40, -30, -20, -20, -30, -40, -50,
                -30, -18, -15, 6, 3, -6, -24, -30,
                -35, -16, 20, 32, 34, 14, -11, -30,
                -34, -5, 24, 35, 34, 35, -16, -35,
                -36, -7, 31, 34, 34, 34, -12, -31,
                -30, -7, 14, 33, 36, 16, -13, -33,
                -36, -27, 5, 2, 5, -1, -31, -33,
                -48, -26, -26, -26, -28, -25, -30, -51)));

    }

    protected ArrayList<ArrayList<Integer>> nextMovelist(HashMap<Integer, Piece> occupied, int color) {
        ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<ArrayList<Integer>>();
        HashMap<Integer, Piece> newOccupied = new HashMap<Integer, Piece>(occupied);
        for (Map.Entry<Integer, Piece> i : newOccupied.entrySet()) {
            int cord = i.getKey();
            Piece p = i.getValue();
            if (p.getColor() != color)
                continue;
            int x = cord / 8;
            int y = cord % 8;
            for (int nex = 0; nex < 8; nex++) {
                for (int ney = 0; ney < 8; ney++) {
                    if (nex == x && ney == y)
                        continue;
                    if (p.checkValidMove(nex, ney, occupied, color, 1)) {
                        possibleMoves.add(new ArrayList<Integer>(Arrays.asList(x, y, nex, ney)));
                    }
                }
            }
        }
        return possibleMoves;
    }

    protected int gamePhaseScore(HashMap<Integer, Piece> occupied) {
        int score = 0;
        for (Map.Entry<Integer, Piece> i : occupied.entrySet()) {
            Piece p = i.getValue();
            String s = p.getFenRep();
            String t = s.toUpperCase();
            if (s.equals(t)) {
                score += pieceValueOpening.get(t);
            } else {
                score -= pieceValueOpening.get(t);
            }
        }
        return score;
    }

    protected int evaluate(HashMap<Integer, Piece> occupied, int whoseTurn) {
        int gamePhaseScore = gamePhaseScore(occupied);
        int openingPhaseScore = 5900;
        int endgamsePhaseScore = 500;
        int gamePhase = -1;
        // 1 = opening 2 = middlegame 3 = ending
        if (gamePhaseScore > openingPhaseScore)
            gamePhase = 1;
        else if (gamePhaseScore < endgamsePhaseScore)
            gamePhase = 3;
        else
            gamePhase = 2;

        int score = 0;
        int scoreOpening = 0;
        int scoreEnding = 0;

        for (Map.Entry<Integer, Piece> i : occupied.entrySet()) {
            int cord = i.getKey();
            int x = cord / 8;
            int y = cord % 8;
            Piece p = i.getValue();
            scoreOpening += pieceValueOpening.get(p.getFenRep());
            scoreEnding += pieceValueEnding.get(p.getFenRep());
            String s = p.getFenRep();
            String t = s.toUpperCase();
            if (s.equals(t)) {
                scoreOpening += positionValueOpening.get(t).get((7 - x) * 8 + y);
                scoreEnding += positionValueEnding.get(t).get((7 - x) * 8 + y);
            } else {
                scoreOpening -= positionValueOpening.get(t).get((x) * 8 + y);
                scoreEnding -= positionValueEnding.get(t).get((x) * 8 + y);
            }
        }

        if (gamePhase == 2) {
            score = (scoreOpening * gamePhaseScore + scoreEnding * (5900 - gamePhaseScore)) / 5900;
        } else if (gamePhase == 1) {
            score = scoreOpening;
        } else {
            score = scoreEnding;
        }
        return (whoseTurn == 1 ? score : -score);
    }

    public ArrayList<Integer> Search(HashMap<Integer, Piece> occupied, int color, int alpha, int beta, int depth) {

        if (depth == 2) {
            int score = evaluate(occupied, color);
            return new ArrayList<Integer>(Arrays.asList(score, 0));
        }

        ArrayList<ArrayList<Integer>> nextMoves = nextMovelist(occupied, color);

        int finScore = (depth % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE), ind = -1;

        for (int i = 0; i < nextMoves.size(); i++) {
            HashMap<Integer, Piece> newOccupied = new HashMap<Integer, Piece>(occupied);
            ArrayList<Integer> arr = nextMoves.get(i);
            int x = arr.get(0), y = arr.get(1), nex = arr.get(2), ney = arr.get(3);
            Piece p = newOccupied.get(x * 8 + y);
            newOccupied.remove(x * 8 + y);
            if (newOccupied.containsKey(nex * 8 + ney)) {
                newOccupied.remove(nex * 8 + ney);
            }

            int px, py;
            boolean phasmoved, pactive;
            px = p.getX();
            py = p.getY();
            phasmoved = p.getHasMoved();
            pactive = p.getActive();
            p.move(nex, ney, newOccupied);

            int score;
            ArrayList<Integer> a = Search(newOccupied, (color ^ 1), alpha, beta, depth + 1);
            score = a.get(0);
            if (depth % 2 == 0) {
                alpha = Math.max(alpha, score);
                if (beta <= alpha) {
                    if (score > finScore) {
                        ind = i;
                        finScore = score;
                    }
                    // undo the move

                    p.setX(px);
                    p.setY(py);
                    p.setHasMoved(phasmoved);
                    p.setActive(pactive);
                    break;
                }
                if (score > finScore) {
                    ind = i;
                    finScore = score;
                }
            } else {
                beta = Math.min(beta, score);
                if (beta <= alpha) {
                    if (score < finScore) {
                        ind = i;
                    }
                    finScore = Math.min(finScore, score);
                    // undo the move
                    p.setX(px);
                    p.setY(py);
                    p.setHasMoved(phasmoved);
                    p.setActive(pactive);
                    break;
                }
                if (score < finScore) {
                    ind = i;
                }
                finScore = Math.min(finScore, score);

            }

            // undo the move
            // newOccupied.put(x * 8 + y, occupied.get(x * 8 + y));
            // if (occupied.containsKey(nex * 8 + ney)) {
            // newOccupied.put(nex * 8 + ney, occupied.get(nex * 8 + ney));
            // } else {
            // if (newOccupied.containsKey(nex * 8 + ney)) {
            // newOccupied.remove(nex * 8 + ney);
            // }
            // }
            p.setX(px);
            p.setY(py);
            p.setHasMoved(phasmoved);
            p.setActive(pactive);
        }
        return new ArrayList<Integer>(Arrays.asList(finScore, ind));
    }

    @Override
    public ArrayList<Integer> nextMove(HashMap<Integer, Piece> prevoccupied, int color) {
        HashMap<Integer, Piece> occupied = new HashMap<Integer, Piece>(prevoccupied);
        ArrayList<ArrayList<Integer>> allmoves = nextMovelist(occupied, color);
        log.info("All moves : {}", allmoves);
        if (prevoccupied.containsKey(0 * 8 + 5)) {
            log.info("{}", prevoccupied.get(0 * 8 + 5));
            log.info("{}", prevoccupied.get(1 * 8 + 6));
            log.info("{}", prevoccupied.get(0 * 8 + 5).checkValidMove(1, 6, occupied, 1, 1));
        }

        // log.info("{}", board.getOccupied().get((nextMove.get(0) * 8 +
        // nextMove.get(1))));
        ArrayList<Integer> searchResult = Search(occupied, color, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        log.info("searchResult in nextMove : {}", searchResult);

        return allmoves.get(searchResult.get(1));
    }

}
