package Z_LLD6_DesignTicTacToe.Controller;

import Z_LLD6_DesignTicTacToe.Exceptions.DuplicateSymbolException;
import Z_LLD6_DesignTicTacToe.Exceptions.MoreThanOneBotException;
import Z_LLD6_DesignTicTacToe.Exceptions.PlayerCountDimensionMismatchException;
import Z_LLD6_DesignTicTacToe.Model.Game;
import Z_LLD6_DesignTicTacToe.Model.GameState;
import Z_LLD6_DesignTicTacToe.Model.Player;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.WinningStrategy;

import java.util.List;

// this class will be stateless as many peoples can start the game at the same time
public class GameController {
    public Game startGame(int dimensionOfBoard,
                   List<Player> players,
                   List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayerCountDimensionMismatchException, MoreThanOneBotException {
        return Game.getBuilder()
                .setDimension(dimensionOfBoard)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

    }
    public void makeMove(Game game){
        game.makeMove();
    }
    void undo(Game game){

    }
    public GameState checkState(Game game){
        return game.getGameState();
    }
    void getWinner(Game game){

    }
    public void printBoard(Game game){
        game.printBoard();
    }

}
