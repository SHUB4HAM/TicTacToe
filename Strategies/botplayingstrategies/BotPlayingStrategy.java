package Z_LLD6_DesignTicTacToe.Strategies.botplayingstrategies;

import Z_LLD6_DesignTicTacToe.Model.Board;
import Z_LLD6_DesignTicTacToe.Model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
