package Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Move;
import Z_LLD6_DesignTicTacToe.Model.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> counts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!counts.containsKey(row)){
            counts.put(row, new HashMap<>());
        }
        Map<Symbol, Integer> rowMap = counts.get(row);
        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol, 0);
        }
        rowMap.put(symbol, rowMap.get(symbol) + 1);

        if(rowMap.get(symbol).equals(board.getSize())){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        Symbol symbol = lastMove.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = counts.get(row);

        rowMap.put(symbol, rowMap.get(symbol) - 1);
    }
}
