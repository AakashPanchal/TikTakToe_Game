import Controllers.GameController;
import Exceptions.InvalidBotNumberException;
import Exceptions.PlayerCountMismatchException;
import Models.*;
import Strategies.WinningStrategy.ColWiningStrategy;
import Strategies.WinningStrategy.DiagonalWinnerStrategy;
import Strategies.WinningStrategy.RowWiningStrategy;
import Strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Models.GameState.IN_PROGRESS;
import static Models.GameState.WIN;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidBotNumberException, PlayerCountMismatchException {

        GameController gameController = new GameController();

        Scanner scanner = new Scanner(System.in);;

        int dimension = 3;

        List<Player> players = new ArrayList<>();

        players.add(
                new Player(1, "aakash" ,new Symbol('X'), PlayerType.HUMAN)
        );
        players.add(
                new Bot(2, "BOT" ,new Symbol('O'), BotDifficultyLevel.EASY)
        );

        List<WinningStrategy> winningStrategies = List.of(
                new RowWiningStrategy(),
                new ColWiningStrategy(),
                new DiagonalWinnerStrategy()
        );

        Game game = gameController.startGame(
                players,
                dimension,
                winningStrategies
        );

        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){

            gameController.printBoard(game);
            System.out.println("Undo y/n?");
            String ans = scanner.next();
            if(ans.equalsIgnoreCase("y")){
                gameController.undoGame(game);
                continue;
            }
            gameController.makeMove(game);

        }


        System.out.println("Game is Ended");
        GameState gameState = gameController.checkState(game);

        if(gameState.equals(GameState.WIN)){
            System.out.println("Winner is " + gameController.gameWinner(game).getName());
        }else{
            System.out.println("It is a DRAW");
        }

    }
}