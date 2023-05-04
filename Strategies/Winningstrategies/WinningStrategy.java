package Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handleUndo(Board board, Move lastMove);
}
