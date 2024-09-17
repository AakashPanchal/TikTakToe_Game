package Strategies.WinningStrategy;

import Models.Board;
import Models.GameState;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWiningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> map = new HashMap<>();


    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();

        Symbol symbol = move.getPlayer().getSymbol();

        if(!map.containsKey(row)){
            map.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> rowMap = map.get(row);

        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }

        rowMap.put(symbol, rowMap.get(symbol) + 1);

        if(rowMap.size() == board.getSize()){
            return true;
        }

        return false;

    }

    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();

        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> colMap = map.get(row);

        colMap.put(symbol,colMap.get(symbol) - 1);


    }
}
