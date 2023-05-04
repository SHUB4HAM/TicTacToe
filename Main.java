package Z_LLD6_DesignTicTacToe;

import OOP4.A_ConstructorChaining.B;
import Z_LLD6_DesignTicTacToe.Controller.GameController;
import Z_LLD6_DesignTicTacToe.Exceptions.DuplicateSymbolException;
import Z_LLD6_DesignTicTacToe.Exceptions.MoreThanOneBotException;
import Z_LLD6_DesignTicTacToe.Exceptions.PlayerCountDimensionMismatchException;
import Z_LLD6_DesignTicTacToe.Model.*;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.ColWinningStrategy;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.DiagonalWinningStrategy;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.RowWinningStrategy;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.WinningStrategy;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DuplicateSymbolException, PlayerCountDimensionMismatchException, MoreThanOneBotException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
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

            List<WinningStrategy> winningStrategies = List.of(
                    new RowWinningStrategy(),
                    new ColWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

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

                System.out.println("Does anyone want to undo: (y/n)");
                String str = scanner.next();

                if(str.equalsIgnoreCase("y")){
                    gameController.undo(game);
                    continue;
                }

                gameController.makeMove(game);
            }
            GameState gameState = gameController.checkState(game);
            if(gameState.equals(GameState.DRAW)){
                System.out.println("Game is draw");
            }
            if(gameState.equals(GameState.WIN)){
                System.out.println(gameController.getWinner(game).getName() + " has won the game");
            }

        } catch(Exception e){
            throw e;
        }



        System.out.println("Game is finished");

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
