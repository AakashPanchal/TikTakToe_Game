package Strategies.WinningStrategy.BotPlayingStrategy;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Move;

import java.util.List;

public class EasyPlayingStrategy implements BotPlayingStrategy{



    public Move makeMove(Board board) {

        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
