package Z_LLD6_DesignTicTacToe.Strategies.botplayingstrategies;

import Z_LLD6_DesignTicTacToe.Model.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel level){
        return new EasyBotPlayingStrategy();
//        if(level.equals(BotDifficultyLevel.EASY)){
//            return new EasyBotPlayingStrategy();
//        } else if(level.equals(BotDifficultyLevel.MEDIUM)){
//            return new MediumBotPlayingStrategy();
//        }
    }
}
