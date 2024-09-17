package Models;

import java.util.Scanner;

public class Player {
    private int id;
    private String Name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(int id, String name, Symbol symbol, PlayerType playerType) {
        this.id = id;
        this.Name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board){
        System.out.println("Enter the row");
        int row = scanner.nextInt();

        System.out.println("Enter the col");
        int col = scanner.nextInt();

        return new Move(new Cell(row,col), this);
    }
}
