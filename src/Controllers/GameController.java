package Controllers;

import Exceptions.InvalidBotNumberException;
import Exceptions.PlayerCountMismatchException;
import Models.Game;
import Models.*;
import Strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    //Game game = new Game();

    public Game startGame(List<Player> players, int dimension,

                          List<WinningStrategy> winningStrategies) throws InvalidBotNumberException, PlayerCountMismatchException {

        return Game.getBuilder()
                .setPlayers(players)
                .setDimension(dimension)
                .setWinningStrategy(winningStrategies)
                .build();
    }

    public void makeMove(Game game){
        game.makeMove();;

    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public Player gameWinner(Game game){
        return game.getWinner();
    }


    public void printBoard(Game game){
        game.printBoard();

    }

    public void undoGame(Game game){
        game.undo();

    }

}
