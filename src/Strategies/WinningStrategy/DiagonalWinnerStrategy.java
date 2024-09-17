package Strategies.WinningStrategy;

import Models.Board;
import Models.GameState;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinnerStrategy implements WinningStrategy{

    Map<Symbol, Integer> leftDia = new HashMap<>();
    Map<Symbol, Integer> rightDia = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        if( row == col){
            if (!leftDia.containsKey(symbol)){
                leftDia.put(symbol, 0);

            }

            leftDia.put(symbol, leftDia.get(symbol) + 1);
        }
        if( row + col == board.getSize() -1){
            if (!rightDia.containsKey(symbol)){
                rightDia.put(symbol, 0);

            }

            rightDia.put(symbol, rightDia.get(symbol) + 1);
        }

        if(row == col){
            if(leftDia.get(symbol) == board.getSize()){
                return true;
            }
        }
        if(row + col == board.getSize() -1){
            if(rightDia.get(symbol) == board.getSize()){
                return true;
            }
        }


        return false;

    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            leftDia.put(symbol, leftDia.get(symbol) -1);
        }
        if(row + col == board.getSize() -1){
            rightDia.put(symbol, rightDia.get(symbol)-1);
        }

    }
}
