package Models;

import Strategies.WinningStrategy.BotPlayingStrategy.BotPlayingStrategy;
import Strategies.WinningStrategy.BotPlayingStrategy.EasyPlayingStrategy;
import Strategies.WinningStrategy.BotPlayingStrategy.botPlayingStrategyFactory;

public class Bot extends Player{



    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    //private Symbol Symbol;

    public Bot(int id, String Name, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(id, Name, symbol, PlayerType.BOT );
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategyFactory.botPlayingStrategy(botDifficultyLevel);

    }

    @Override
    public Move makeMove(Board board) {

        Move move = botPlayingStrategy.makeMove(board);

        move.setPlayer(this);
        return move;
    }
}


