package Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Move;
import Z_LLD6_DesignTicTacToe.Model.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> counts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!counts.containsKey(col)){
            counts.put(col, new HashMap<>());
        }
        Map<Symbol, Integer> colMap = counts.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol) + 1);

        if(colMap.get(symbol).equals(board.getSize())){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        int col = lastMove.getCell().getCol();
        Symbol symbol = lastMove.getPlayer().getSymbol();

        Map<Symbol, Integer> colMap = counts.get(col);

        colMap.put(symbol, colMap.get(symbol) - 1);
    }


}
