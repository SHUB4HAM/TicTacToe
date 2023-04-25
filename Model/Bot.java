package Z_LLD6_DesignTicTacToe.Model;

import Z_LLD6_DesignTicTacToe.Strategies.botplayingstrategies.BotPlayingStrategy;
import Z_LLD6_DesignTicTacToe.Strategies.botplayingstrategies.BotPlayingStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, String name, Long id, BotDifficultyLevel botDifficultyLevel){
        super(symbol, name, id, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board){
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
