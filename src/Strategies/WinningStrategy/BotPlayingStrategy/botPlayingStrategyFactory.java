package Strategies.WinningStrategy.BotPlayingStrategy;

import Models.BotDifficultyLevel;

public class botPlayingStrategyFactory {

    public static BotPlayingStrategy botPlayingStrategy(BotDifficultyLevel botDifficultyLevel){

        //TODO: diffrent playing strategies need to impliment
        return new EasyPlayingStrategy();
    }
}
