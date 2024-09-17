package Models;

import Exceptions.InvalidBotNumberException;
import Exceptions.PlayerCountMismatchException;
import Strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {

    public List<Player> getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return Winner;
    }

    public List<Move> getMove() {
        return move;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    private List<Player> player ;

    private Board board;

    private GameState gameState;

    private Player Winner;

    private List<Move> move;

    private int nextMovePlayerIndex;

    private List<WinningStrategy> winningStrategies;

    private Game(List<Player> players,int dimension, List<WinningStrategy> winningStrategies){
        this.player = players;
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.move = new ArrayList<>();
        this.nextMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;

    }


    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{

        private List<Player> players;

        private int dimension;

        private List<WinningStrategy> winningStrategies;

        private Symbol symbol;

        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }

        public Builder addWiningStrategy(WinningStrategy winningStrategy){
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public Builder setSymbol(Symbol symbol){
            this.symbol = symbol;
            return this;
        }

        //TODO: validate code should be in diffrent class

        public void validatePlayers() throws PlayerCountMismatchException {
            if(players.size() != dimension - 1){
                throw new PlayerCountMismatchException();
            }
        }

        public void validateBot() throws InvalidBotNumberException {
            int count = 0;
            for(Player player : players){
                if(player.equals(PlayerType.BOT)){
                    count++;

                }
            }
            if(count > 1){
                throw new InvalidBotNumberException();
            }
        }

        public void validateSymbol(){
//            HashSet<char> symbol1 = new HashSet<char>()
//            if(symbol.getSymbol() == 'A'){
//
//            }

        }

        public Game build() throws PlayerCountMismatchException, InvalidBotNumberException {
            validatePlayers();
            validateBot();
            validateSymbol();
            return new Game(players,dimension,winningStrategies);
        }



    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public void setMove(List<Move> move) {
        this.move = move;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;


    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()){
            return false;
        }
        if(col >= board.getSize()){
            return false;
        }
        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return true;
        }

        return false;
    }

    private boolean checkWinner(Board board, Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;

            }
        }

        return false;
    }

    public void makeMove(){
        Player currentPlayerMove = player.get(nextMovePlayerIndex);

        System.out.println("it is" + currentPlayerMove.getName()+ " 's move");

        Move moves = currentPlayerMove.makeMove(board);

        System.out.println(currentPlayerMove.getName() + " has made a move at" + moves.getCell().getRow()
                + " Row and " + moves.getCell().getCol() + " column");

        if(!validateMove(moves)){
            System.out.println("Invalid Move! Please try agian!");
            return;
        }

        int row = moves.getCell().getRow();
        int col = moves.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);

        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentPlayerMove);

        Move finalMove = new Move(cellToChange, currentPlayerMove);
        move.add(finalMove);

        nextMovePlayerIndex += 1;

        nextMovePlayerIndex %= player.size();

        if(checkWinner(board, finalMove)){
            Winner  = currentPlayerMove;
            gameState = GameState.WIN;
        }
        else if(move.size() == board.getSize() * board.getSize()){
            gameState = GameState.DRAW;;
        }

    }

    public void undo(){
        if(move.size() == 0){
            System.out.println("No Moves to undo");
            return;
        }

        Move lastMove = move.get(move.size() -1);
        move.remove(lastMove);;

        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(board,lastMove);
        }

        nextMovePlayerIndex -= 1;
        nextMovePlayerIndex = (nextMovePlayerIndex + player.size()) % player.size();


    }

    public void printBoard(){
        board.printBoard();
    }


}

