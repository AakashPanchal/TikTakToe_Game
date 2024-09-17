package Strategies.WinningStrategy;

import Models.Board;
import Models.GameState;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWiningStrategy implements WinningStrategy{

    private Map<Integer, Map<Symbol, Integer>> map = new HashMap<>();


    @Override
    public boolean checkWinner(Board board, Move move) {

        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        if(!map.containsKey(col)){
            map.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> colMap = map.get(col);

        if(!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }

        colMap.put(symbol, colMap.get(symbol) + 1);

        if(colMap.size() == board.getSize()){
            return true;
        }

        return false;

    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> colMap = map.get(col);

        colMap.put(symbol,colMap.get(symbol) - 1);


    }
}
