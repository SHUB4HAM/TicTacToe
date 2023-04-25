package Z_LLD6_DesignTicTacToe.Strategies.botplayingstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Cell;
import Z_LLD6_DesignTicTacToe.Model.CellState;
import Z_LLD6_DesignTicTacToe.Model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row: board.getBoard()){
            for(Cell cell: row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
