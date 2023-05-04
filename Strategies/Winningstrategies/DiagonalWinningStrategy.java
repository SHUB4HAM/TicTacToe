package Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Move;
import Z_LLD6_DesignTicTacToe.Model.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiag = new HashMap<>();
    private Map<Symbol, Integer> rightDiag = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row == col){   // means left diagonal
            leftDiag.put(symbol, leftDiag.getOrDefault(symbol, 0) + 1);
            if(leftDiag.get(symbol).equals(board.getSize())){
                return true;
            }
        }
        if(row+col == board.getSize()-1){   // means right diagonal
            rightDiag.put(symbol, rightDiag.getOrDefault(symbol, 0) + 1);
            if(rightDiag.get(symbol).equals(board.getSize())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        Symbol symbol = lastMove.getPlayer().getSymbol();
        if(row == col){   // means left diagonal
            leftDiag.put(symbol, leftDiag.get(symbol) - 1);
        }
        if(row+col == board.getSize()-1){   // means right diagonal
            rightDiag.put(symbol, rightDiag.get(symbol) - 1);
        }
    }
}
