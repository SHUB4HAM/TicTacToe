package Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Move;
import Z_LLD6_DesignTicTacToe.Model.Symbol;

import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    List<Map<Symbol, Integer>> symbolCount;
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
