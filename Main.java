package Z_LLD6_DesignTicTacToe;

import OOP4.A_ConstructorChaining.B;
import Z_LLD6_DesignTicTacToe.Controller.GameController;
import Z_LLD6_DesignTicTacToe.Model.*;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        System.out.println("Game is starting");

        try{
            int dimension = 3;
            List<Player> players = new ArrayList<>();
            players.add(
                    new Player(new Symbol('X'), "Shubham", 1L, PlayerType.HUMAN)
            );
            players.add(
                    new Bot(new Symbol('0'),"GPT", 2L, BotDifficultyLevel.HARD)
            );

            List<WinningStrategy> winningStrategies = new ArrayList<>();
//            winningStrategies.add();

            Game game = gameController.startGame(
                    dimension,
                    players,
                    winningStrategies
            );
            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
                // 1) print the board
                // 2) x's turn
                // 3) ask to make a move
                gameController.printBoard(game);

                gameController.makeMove(game);

            }

        } catch(Exception e){
            System.out.println("Something wrong happened");
        }

        System.out.println("Game is created");





//        while(gameController.getStatus(game).equals(GameState.IN_PROGRESS)){
//            gameController.printBoard(game);
//            gameController.nextMove(game);
//        }
//        if(gameController.getStatus(game).equals(GameState.DRAW)){
//            System.out.println("Game has Drawn");
//        } else{
//            System.out.println("Game has won");
//        }
    }
}
