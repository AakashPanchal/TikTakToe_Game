package Strategies.WinningStrategy;

import Models.Board;
import Models.GameState;
import Models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);
}
